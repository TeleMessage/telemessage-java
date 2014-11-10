package telemessage.rest.reader;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Class to parse JSON
 * @author Grinfeld Mikhail
 * @since 5/27/2014.
 */
public class JsonReader {

    public static final char
        START_MAP = '{',
        END_MAP = '}',
        START_ARRAY = '[',
        END_ARRAY = ']',
        ESCAPE_CHAR = '\\',
        STRING_CHAR = '"',
        CHAR_CHAR = '\'',
        ELEM_DELIM = ',',
        VALUE_DELIM = ':',
        SPACE_CHAR = ' ',
        EMPTY_CHAR = '\0';


    public static <T> T read(InputStream in, Class<T> clazz) throws IOException, IllegalArgumentException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        try {
            Object r = new JsonReader().parse(bf);
            return r == null ? null : clazz.cast(r);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("Failed to convert Json to Object", e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to convert Json to Object", e);
        }
    }

    LinkedList<Object> queue = new LinkedList<Object>();

    private JsonReader() {
    }

    private Object parse(BufferedReader bf) throws IOException, IllegalArgumentException, InstantiationException, IllegalAccessException {
        Pair<Character, Object> resp = parseRecursive(bf);
        if (resp != null && resp.getRight() != null) {
            return resp.getRight();
        }
        return null;
    }

    /**
     * From this method starts our recursion
     * @param bf reader
     * @return returns Pair of last read character and parsed object
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Pair<Character, Object> parseRecursive(BufferedReader bf) throws IOException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        StringBuilder sb = new StringBuilder();
        char c = '\0';
        int r = -1;
        while ( (r = bf.read()) != -1 ) {
            c = (char) r;
            Pair<Character, Object> p = parseStructure(bf, c);
            Object o = p.getRight();
            c = p .getLeft();
            if (o != null) {
                return new ImmutablePair<Character, Object>(c, o);
            } else if (c != ELEM_DELIM) {
                sb.append(c);
            }
        }
        return new ImmutablePair<Character, Object>(c, null);
    }

    private Pair<Character, Object> parseListInnerElement(BufferedReader bf, char c) throws IOException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        StringBuilder sb = new StringBuilder();
        do {
            Pair<Character, Object> p = parseStructure(bf, c);
            Object o = p.getRight();
            c = p.getLeft();
            if (o != null) {
                return p;
            } else if (c != ELEM_DELIM) {
                if (!(sb.length() == 0 && c == EMPTY_CHAR)) // avoid empty string at the beginning
                    sb.append(c);
            }
            int r = bf.read();
            c = r != -1 ? (char) r : EMPTY_CHAR;
        } while (bf.ready());
        return new ImmutablePair<Character, Object>(c, null);
    }

    private Object createClass(Map<String, Object> possible) throws IllegalAccessException, InstantiationException {
        String className = (String)possible.get("class");
        if (StringUtils.isEmpty(className))
            return possible;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            return possible;
        }
        if (clazz == null)
            return possible;

        Object o = clazz.newInstance();
        List<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        Class<?> inherit = clazz;
        while ((inherit = inherit.getSuperclass()) != null) {
            fields.addAll(Arrays.asList(inherit.getDeclaredFields()));
        }
        for (Field f : fields) {
            Object data = possible.get(f.getName());
            if (data != null) {
                f.setAccessible(true);
                fill(f, o, data);
            }
        }


        return o;
    }

    private void fill(Field f, Object owner, Object data) throws IllegalArgumentException, IllegalAccessException {
        if (data == null || (data instanceof String && "".equals(data))) {
            data = null;
        }
        if (f.getType().isPrimitive() && data == null) {
            throw new IllegalArgumentException("Incompatible types for " + f.getName());
        }
        if (f.getType().equals(Boolean.class) || f.getType().equals(Boolean.TYPE)) {
            String value = (String)data;
            f.setBoolean(owner, !("".equals(value) || "0".equals(value) || "false".equalsIgnoreCase(value)));
        } else if (f.getType().equals(Byte.class) || f.getType().equals(Byte.TYPE)) {
            f.setByte(owner, Byte.parseByte((String) data));
        } else if (f.getType().equals(Short.class) || f.getType().equals(Short.TYPE)) {
            f.setShort(owner, Short.parseShort((String) data));
        } else if (f.getType().equals(Integer.class) || f.getType().equals(Integer.TYPE)) {
            f.setInt(owner, Integer.parseInt((String)data));
        }else if (f.getType().equals(Long.class) || f.getType().equals(Long.TYPE)) {
            f.setLong(owner, Long.parseLong((String) data));
        } else if (f.getType().equals(Double.class) || f.getType().equals(Double.TYPE)) {
            f.setDouble(owner, Double.parseDouble((String) data));
        } else if (f.getType().equals(Float.class) || f.getType().equals(Float.TYPE)) {
            f.setFloat(owner, Float.parseFloat((String) data));
        } else if (f.getType().equals(String.class)) {
            f.set(owner, data);
        } else if (f.getType().isArray() && data != null && data instanceof List) {
            List<?> l = ((List) data);
            Object ar = Array.newInstance(f.getType().getComponentType(), l.size());
            System.arraycopy(l.toArray(), 0, ar, 0, l.size());
            f.set(owner, ar);
        } else {
            f.set(owner, f.getType().cast(data));
        }

    }

    /**
     * Method determinate if structure is complicated and tries to parse it into Array, Map, Object (if class parameter exists), String or Number
     * @param bf reader
     * @param c current character
     * @return pair of last read character and parsed object
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private Pair<Character, Object> parseStructure(BufferedReader bf, char c) throws IOException, IllegalArgumentException, InstantiationException, IllegalAccessException {
        Map<String, Object> m = null;
        List<Object> l = null;
        StringBuilder sb = new StringBuilder();
        String pn = null;
        switch (c) {
            case START_MAP:
                m = new HashMap<String, Object>();
                queue.offer(m);
                c = parseMap(bf, m);
                if (c == END_MAP) {
                    m = (Map<String,Object>)queue.pollLast();
                    if (m.containsKey("class")) {
                        Object o = createClass(m);
                        int r = bf.read();
                        return new ImmutablePair<Character, Object>(r != -1 ? (char)r : c, o);
                    }
                    return new ImmutablePair<Character, Object>(c, m);
                }
                break;
            case START_ARRAY:
                l = new ArrayList<Object>();
                queue.offer(l);
                c = parseList(bf, l);
                if (c == END_ARRAY) {
                    l = (List<Object>)queue.pollLast();
                    int r = bf.read();
                    return new ImmutablePair<Character, Object>(r != -1 ? (char)r : c, l);
                }
                break;
            case STRING_CHAR:
            case CHAR_CHAR:
                sb = new StringBuilder();
                queue.offer(sb);
                c = parseString(bf, sb);
                pn = sb.toString().trim();
                queue.pollLast();
                if (pn.equalsIgnoreCase("null"))
                    return new ImmutablePair<Character, Object>(c, "");
                return new ImmutablePair<Character, Object>(c, pn);
            default:
                sb = new StringBuilder();
                sb.append(c);
                queue.offer(sb);
                c = parseNumber(bf, sb);
                pn = sb.toString().trim();
                queue.pollLast();
                if (pn.equalsIgnoreCase("null"))
                    return new ImmutablePair<Character, Object>(c, "");
                return new ImmutablePair<Character, Object>(c, pn);
        }

        return new ImmutablePair<Character, Object>(c, null);
    }

    private char parseMap(BufferedReader bf, Map<String, Object> m) throws IOException, IllegalArgumentException, InstantiationException, IllegalAccessException {
        char c = SPACE_CHAR;
        int r = -1;
        while ( (r = bf.read()) != -1) {
            c = (char) r;
            if (c == END_MAP) {
                return c;
            } else {
                StringBuilder sb = new StringBuilder();
                // searching key
                do {
                    if (c == -1)
                        throw new IllegalArgumentException("Reached end of stream - un-parsed data");
                    if (c != ELEM_DELIM)
                        sb.append(c);
                } while ((r = bf.read()) != -1 && (c = (char)r) != VALUE_DELIM);
                String key = sb.toString().trim();
                if (key.startsWith(String.valueOf(CHAR_CHAR)) || key.startsWith(String.valueOf(STRING_CHAR)))
                    key = key.substring(1);
                if (key.endsWith(String.valueOf(CHAR_CHAR)) || key.endsWith(String.valueOf(STRING_CHAR)))
                    key = key.substring(0, key.length() - 1);

                Pair<Character, Object> p = parseRecursive(bf);
                Object o = p.getRight();
                c = p.getLeft();
                m.put(key, o);

                if (c == END_MAP) {
                    return c;
                }
            }
        }

        return c;
    }

    private char parseList(BufferedReader bf, List<Object> l) throws IOException, IllegalArgumentException, InstantiationException, IllegalAccessException {
        char c = SPACE_CHAR;
        int r = -1;
        while ((r = bf.read()) != -1) {
            c = (char) r;
            if (c == END_MAP) {
                return c;
            } else if (c == ELEM_DELIM || c == VALUE_DELIM) {
                // do nothing
            } else {
                Pair<Character, Object> p = parseListInnerElement(bf, c);
                Object o = p.getRight();
                c = p.getLeft();
                l.add(o);
                if (c == END_ARRAY) {
                    return c;
                }
            }
        }


        return c;
    }

    private char parseNumber(BufferedReader bf, StringBuilder sb) throws IOException, IllegalArgumentException {
        char c = SPACE_CHAR;
        int r = -1;
        while ((r = bf.read()) != -1) {
            c = (char) r;
            switch (c) {
                case END_ARRAY:
                case END_MAP:
                case ELEM_DELIM:
                    return c;
            }
            sb.append(c);
        }
        return c;
    }

    private char parseString (BufferedReader bf, StringBuilder sb) throws IOException, IllegalArgumentException {
        char c = SPACE_CHAR;
        char prevC = SPACE_CHAR;
        int r = -1;
        while ( (r = bf.read()) != -1 && (c = (char) r) != -1 && !(prevC != ESCAPE_CHAR && (c == CHAR_CHAR || c == STRING_CHAR))) {
            sb.append(c);
            prevC = c;
        }
        return c;
    }
}

