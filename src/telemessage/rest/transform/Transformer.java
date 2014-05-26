package telemessage.rest.transform;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public interface Transformer {
    public boolean match(Object o);
    public void transform(Object o, OutputStream out) throws IOException, IllegalAccessException;
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException;
}
