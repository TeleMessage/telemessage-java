package telemessage.web.rest;

import java.lang.annotation.ElementType;

/**
 * Defines field to be serialized for REST. Only if class annotated with {@link RestClass}
 */
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.FIELD})
public @interface RestField {

}