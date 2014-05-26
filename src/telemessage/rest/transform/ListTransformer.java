package telemessage.rest.transform;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class ListTransformer implements Transformer {
    @Override
    public boolean match(Object o) {
        return List.class.isAssignableFrom(o.getClass());
    }

    @Override
    public void transform(Object o, OutputStream out) throws IOException, IllegalAccessException {
        List<?> l = (List<?>)o;
        out.write("[".getBytes());
        for (int i=0; i<l.size(); i++) {
            Object p = l.get(i);
            TransformerFactory.get(p).transform(p, out);
            if (i != l.size() - 1)
                out.write(",".getBytes());
        }
        out.write("]".getBytes());
    }

    @Override
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException {
        List<?> l = (List<?>)o;
        out.append("[");
        for (int i=0; i<l.size(); i++) {
            Object p = l.get(i);
            TransformerFactory.get(p).transform(p, out);
            if (i != l.size() - 1)
                out.append(",");
        }
        out.append("]");
    }
}
