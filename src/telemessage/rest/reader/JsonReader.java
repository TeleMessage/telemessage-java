package telemessage.rest.reader;

import org.apache.commons.lang3.StringUtils;

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
            if (r == null)
                return null;
            List<?> l = (List<?>)r;
            if (l.size() == 0)
                return null;
            return clazz.cast(l.get(0));
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
        parse1(bf);
        if (queue.size() == 1)
            return queue.getFirst();
        return null;
    }

    private Object parse1(BufferedReader bf) throws IOException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        StringBuilder sb = new StringBuilder();
        char c = '\0';
        int r = -1;
        while ( (r = bf.read()) != -1 ) {
            c = (char) r;
            Object o = parse2(bf, c);
            if (o != null) {
                return o;
            } else if (c != ELEM_DELIM) {
                sb.append(c);
            }
        }
        return null;
    }

    private Object parse3(BufferedReader bf, char c) throws IOException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        StringBuilder sb = new StringBuilder();
        do {
            Object o = parse2(bf, c);
            if (o != null) {
                return o;
            } else if (c != ELEM_DELIM) {
                if (!(sb.length() == 0 && c == EMPTY_CHAR)) // avoid empty string at the beginning
                    sb.append(c);
            }
            int r = bf.read();
            c = r != -1 ? (char) r : EMPTY_CHAR;
        } while (bf.ready());
        return null;
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
            f.set(owner, Array.newInstance(f.getType().getComponentType(), l.size()));
        } else {
            f.set(owner, f.getType().cast(data));
        }

    }

    private Object parse2(BufferedReader bf, char c) throws IOException, IllegalArgumentException, InstantiationException, IllegalAccessException {
        Map<String, Object> m = null;
        List<Object> l = null;
        StringBuilder sb = new StringBuilder();
        String pn = null;
        switch (c) {
            case START_MAP:
                m = new HashMap<String, Object>();
                queue.offer(m);
                c = parseMap(bf, m);
                if (c == END_ARRAY || c == END_MAP) {
                    m = (Map<String, Object>)queue.pollLast();
                    return createClass(m);
                }
                break;
            case END_MAP:
                m = (Map<String, Object>)queue.pollLast();
                return createClass(m);
            case START_ARRAY:
                l = new ArrayList<Object>();
                queue.offer(l);
                c = parseList(bf, l);
                if (c == END_ARRAY || c == END_MAP) {
                    l = (List<Object>)queue.pollLast();
                    return l;
                }
                break;
            case END_ARRAY:
                l = (List<Object>)queue.pollLast();
                return l;
            case STRING_CHAR:
            case CHAR_CHAR:
                sb = new StringBuilder();
                queue.offer(sb);
                parseString(bf, sb);
                pn = sb.toString().trim();
                queue.pollLast();
                if (pn.equalsIgnoreCase("null"))
                    return "";
                return pn;
            default:
                sb = new StringBuilder();
                sb.append(c);
                queue.offer(sb);
                parseNumber(bf, sb);
                pn = sb.toString().trim();
                queue.pollLast();
                if (pn.equalsIgnoreCase("null"))
                    return "";
                return pn;
        }

        return null;
    }

    private char parseMap(BufferedReader bf, Map<String, Object> m) throws IOException, IllegalArgumentException, InstantiationException, IllegalAccessException {
        boolean reachEndOfMap = false;
        char c = SPACE_CHAR;
        int r = -1;
        while ( (r = bf.read()) != -1 && !reachEndOfMap) {
            c = (char) r;
            if (c == END_MAP || c == END_ARRAY) {
                reachEndOfMap = true;
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

                Object o = parse1(bf);
                m.put(key, o);
            }
        }

        return c;
    }

    private char parseList(BufferedReader bf, List<Object> l) throws IOException, IllegalArgumentException, InstantiationException, IllegalAccessException {
        char c = SPACE_CHAR;
        boolean reachEndOfMap = false;
        int r = -1;
        while ((r = bf.read()) != -1 && !reachEndOfMap) {
            c = (char) r;
            if (c == END_ARRAY) {
                reachEndOfMap = true;
            } else if (c == ELEM_DELIM) {
                // do nothing
            } else {
                Object o = parse3(bf, c);
                l.add(o);
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

