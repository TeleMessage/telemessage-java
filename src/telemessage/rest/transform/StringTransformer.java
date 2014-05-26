package telemessage.rest.transform;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class StringTransformer implements Transformer {
    @Override
    public boolean match(Object o) {
        return o.getClass().equals(CharSequence.class) || o.getClass().equals(String.class);
    }

    @Override
    public void transform(Object o, OutputStream out) throws IOException {
        String s = (String)o;
        out.write(("\"" + StringEscapeUtils.escapeEcmaScript(s) + "\"").getBytes());
    }

    @Override
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException {
        String s = (String)o;
        out.append(("\"" + StringEscapeUtils.escapeEcmaScript(s) + "\""));
    }
}
