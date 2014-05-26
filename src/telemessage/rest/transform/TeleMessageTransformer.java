package telemessage.rest.transform;

import telemessage.web.rest.RestClass;
import telemessage.web.rest.RestField;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class TeleMessageTransformer implements Transformer {
    @Override
    public boolean match(Object o) {
        return o.getClass().getAnnotation(RestClass.class) != null;
    }

    @Override
    public void transform(Object o, OutputStream out) throws IOException, IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        Object psik = null;
        out.write("{".getBytes());
        int count = 0;
        for (Field f : fields) {
            String name = f.getName();
            f.setAccessible(true);
            Object part = f.get(o);
            if (part != null && f.getAnnotation(RestField.class) != null) {
                if (psik != null) {
                    out.write(",".getBytes());
                }
                out.write("\"".getBytes());
                out.write(name.getBytes());
                out.write("\":".getBytes());
                TransformerFactory.get(part).transform(part, out);
                count++;
                psik = part;
            }
        }
        if (count > 0) {
            out.write((", \"class\": \"" + o.getClass().getName() + "\"").getBytes());
        }
        out.write("}".getBytes());
    }

    @Override
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        Object psik = null;
        out.append("{");
        int count = 0;
        for (Field f : fields) {
            String name = f.getName();
            f.setAccessible(true);
            Object part = f.get(o);
            if (part != null && f.getAnnotation(RestField.class) != null) {
                if (psik != null) {
                    out.append(",");
                }
                out.append("\"");
                out.append(name);
                out.append("\":");
                TransformerFactory.get(part).transform(part, out);
                count++;
                psik = part;
            }
        }
        if (count > 0) {
            out.append((", \"class\": \"" + o.getClass().getName() + "\""));
        }
        out.append("}");
    }
}
