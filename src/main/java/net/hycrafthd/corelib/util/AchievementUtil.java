package net.hycrafthd.corelib.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

import net.minecraft.stats.Achievement;

public class AchievementUtil {

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
