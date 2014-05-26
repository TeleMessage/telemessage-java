package telemessage.rest.transform;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class ArrayTransformer implements Transformer {
    @Override
    public boolean match(Object o) {
        return o.getClass().isArray();
    }

    @Override
    public void transform(Object o, OutputStream out) throws IOException, IllegalAccessException {
        out.write("[".getBytes());
        int length = Array.getLength(o);
        for (int i=0; i<length; i++) {
            Object p = Array.get(o, i);
            TransformerFactory.get(p).transform(p, out);
            if (i != length - 1)
                out.write(",".getBytes());
        }
        out.write("]".getBytes());
    }

    @Override
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException {
        out.append("[");
        int length = Array.getLength(o);
        for (int i=0; i<length; i++) {
            Object p = Array.get(o, i);
            TransformerFactory.get(p).transform(p, out);
            if (i != length - 1)
                out.append(",");
        }
        out.append("]");
    }
}
