package net.hycrafthd.corelib.util.event;

import java.lang.annotation.*;

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
