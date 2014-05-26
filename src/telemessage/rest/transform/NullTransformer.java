package telemessage.rest.transform;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class NullTransformer implements Transformer {
    @Override
    public boolean match(Object o) {
        return o == null;
    }

    @Override
    public void transform(Object o, OutputStream out) throws IOException {
        out.write("\"\"".getBytes());
    }

    @Override
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException {
        out.append("\"\"");
    }
}
