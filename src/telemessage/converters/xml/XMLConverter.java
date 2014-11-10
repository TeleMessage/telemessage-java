package telemessage.converters.xml;

public interface XMLConverter<T> {
    public T convert(Object...args);
}
