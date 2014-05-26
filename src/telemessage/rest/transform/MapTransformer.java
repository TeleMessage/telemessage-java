package telemessage.rest.transform;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class MapTransformer implements Transformer {
    @Override
    public boolean match(Object o) {
        return Map.class.isAssignableFrom(o.getClass());
    }

    @Override
    public void transform(Object o, OutputStream out) throws IOException, IllegalAccessException {
        Map<?, ?> m = (Map<?, ?>)o;
        int i=0;
        out.write("{".getBytes());
        for (Map.Entry<?, ?> entry : m.entrySet()) {
            String key = "\"" + StringEscapeUtils.escapeEcmaScript(String.valueOf(entry.getKey())) + "\"";
            out.write(key.getBytes());
            out.write(":".getBytes());
            Object p = entry.getValue();
            TransformerFactory.get(p).transform(p, out);
            if (i != m.size() - 1)
                out.write(",".getBytes());
            i++;
        }
        out.write("}".getBytes());
    }

    @Override
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException {
        Map<?, ?> m = (Map<?, ?>)o;
        int i=0;
        out.append("{");
        for (Map.Entry<?, ?> entry : m.entrySet()) {
            String key = "\"" + StringEscapeUtils.escapeEcmaScript(String.valueOf(entry.getKey())) + "\"";
            out.append(key);
            out.append(":");
            Object p = entry.getValue();
            TransformerFactory.get(p).transform(p, out);
            if (i != m.size() - 1)
                out.append(",");
            i++;
        }
        out.append("}");
    }
}
