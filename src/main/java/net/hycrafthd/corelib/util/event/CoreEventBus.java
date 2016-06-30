package net.hycrafthd.corelib.util.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import net.hycrafthd.corelib.CoreLib;

/**
 * CoreEvent bus
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class CoreEventBus {

	/**
	 * Listener list
	 */
	private ConcurrentHashMap<Object, ArrayList<Method>> listenerlist = new ConcurrentHashMap<Object, ArrayList<Method>>();

	/**
	 * Register an object for event handler
	 * 
	 * @param obj
	 *            EventHandler class
	 */
	public void register(Object obj) {
		if (listenerlist.containsKey(obj)) {
			return;
		}
		ArrayList<Method> methods = new ArrayList<Method>();
		for (Method method : obj.getClass().getDeclaredMethods()) {
			method.setAccessible(true);
			if (method.isAnnotationPresent(SubscribeCoreEvent.class)) {
				if (method.getParameterCount() == 1) {
					if (CoreEvent.class.isAssignableFrom(method.getParameters()[0].getType())) {
						methods.add(method);
					}
				}
			}
		}
		listenerlist.put(obj, methods);
	}

	/**
	 * Unregister an object from listener list
	 * 
	 * @param obj
	 *            Eventhandler class
	 */
	public void unregister(Object obj) {
		if (listenerlist.containsKey(obj)) {
			listenerlist.remove(obj);
		}
	}

	/**
	 * Post event to all event handler
	 * 
	 * @param event
	 *            Event instance
	 */
	public void post(CoreEvent event) {
		Enumeration<Object> enumeration = listenerlist.keys();
		while (enumeration.hasMoreElements()) {
			Object obj = enumeration.nextElement();
			for (Method method : listenerlist.get(obj)) {
				method.setAccessible(true);
				if (method.getParameters()[0].getType().equals(event.getClass())) {
					try {
						method.invoke(obj, event);
					} catch (Throwable th) {
						CoreLib.getLogger().error("Failed posting event " + event + " to method " + method, th);
					}
				}
			}
		}
	}

}
