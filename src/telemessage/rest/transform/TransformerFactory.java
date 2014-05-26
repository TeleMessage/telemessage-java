package telemessage.rest.transform;

/**
 * @author Grinfeld Mikhail
 * @since 5/25/2014.
 */
public class TransformerFactory {

    private static Transformer[] transformers = new Transformer[] {
        new PrimitiveTransformer(),
        new StringTransformer(),
        new ArrayTransformer(),
        new ListTransformer(),
        new MapTransformer(),
        new TeleMessageTransformer(),
        new ObjectTransformer()
    };

    final static Transformer Null = new NullTransformer();

    public static Transformer get(Object o) {
        if (Null.match(o))
            return Null;
        for (Transformer t : transformers) {
            if (t.match(o))
                return t;
        }
        return null;
    }
}
