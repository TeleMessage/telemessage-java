package telemessage.rest.transform;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class ObjectTransformer implements Transformer {
    @Override
    public boolean match(Object o) {
        return true;
    }

    @Override
    public void transform(Object o, OutputStream out) throws IOException, IllegalAccessException {
        Field[] fields = o.getClass().getFields();
        int counter = 0;
        Object psik = null;
        out.write("{".getBytes());
        for (Field f : fields) {
            String name = f.getName();
            f.setAccessible(true);
            Object part = f.get(o);
            if (part != null) {
                if (psik != null) {
                    out.write(",".getBytes());
                }
                out.write("\"".getBytes());
                out.write(name.getBytes());
                out.write("\":".getBytes());
                TransformerFactory.get(part).transform(part, out);
                psik = part;
                counter++;
            }
        }
        if (counter > 0) {
            out.write((", \"class\": \"" + o.getClass().getName() + "\"").getBytes());
        }
        out.write("}".getBytes());
    }

    @Override
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException {
        Field[] fields = o.getClass().getFields();
        int counter = 0;
        Object psik = null;
        out.append("{");
        for (Field f : fields) {
            String name = f.getName();
            f.setAccessible(true);
            Object part = f.get(o);
            if (part != null) {
                if (psik != null) {
                    out.append(",");
                }
                out.append("\"");
                out.append(name);
                out.append("\":");
                TransformerFactory.get(part).transform(part, out);
                psik = part;
                counter++;
            }
        }
        if (counter > 0) {
            out.append((", \"class\": \"" + o.getClass().getName() + "\""));
        }
        out.append("}");
    }
}
