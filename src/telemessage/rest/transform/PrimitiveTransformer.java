package telemessage.rest.transform;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class PrimitiveTransformer implements Transformer {

    @Override
    public boolean match(Object o) {
        Class<?> clazz = o.getClass();
        return clazz.isPrimitive() || Boolean.class.equals(clazz) || Byte.class.equals(clazz) ||
            Short.class.equals(clazz) || Character.class.equals(clazz) ||
            Integer.class.equals(clazz) || Long.class.equals(clazz) ||
            Double.class.equals(clazz) || Float.class.equals(clazz);
    }

    @Override
    public void transform(Object o, OutputStream out) throws IOException {
        out.write(String.valueOf(o).getBytes());
    }

    @Override
    public void transform(Object o, StringBuilder out) throws IOException, IllegalAccessException {
        out.append(String.valueOf(o));
    }
}
