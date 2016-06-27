package net.hycrafthd.corelib.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

import net.minecraft.stats.Achievement;

/**
 * Util methods for {@link Achievement}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class AchievementUtil {

	/**
	 * Get all initialized achievement fields as a list
	 * 
	 * @param clazz
	 *            Achievement class
	 * @return Return all initialized achievements as a list
	 */
	public static Achievement[] fromClass(Class<?> clazz) {
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		for (Field field : clazz.getFields()) {
			try {
				if (field.getType() == Achievement.class) {
					Achievement ach = (Achievement) field.get(null);
					if (ach == null) {
						continue;
					}
					achievements.add(ach);
				}
			} catch (Exception e) {
			}
		}
		return (Achievement[]) achievements.toArray(new Achievement[achievements.size()]);
	}

}
