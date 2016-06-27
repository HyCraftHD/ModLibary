package net.hycrafthd.corelib.util.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for Coreeventhandler methods
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubscribeCoreEvent {

}
