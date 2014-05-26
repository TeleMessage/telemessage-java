package telemessage.converters.xml;

public interface XMLConverter<T, K> {
    public T convert(K k, Object...args);
}
