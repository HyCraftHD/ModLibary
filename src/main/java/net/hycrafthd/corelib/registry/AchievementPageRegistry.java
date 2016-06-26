package net.hycrafthd.corelib.registry;

import net.minecraftforge.common.AchievementPage;

/**
 * Registry for {@link AchievementPage}
 * 
 * @author HyCraftHD (https://www.hycrafthd.net)
 *
 */
public class AchievementPageRegistry {

	/**
	 * Register a new {@link AchievementPage}
	 * 
	 * @param page
	 *            AchievementPage instance
	 */
	public static void register(AchievementPage page) {
		AchievementPage.registerAchievementPage(page);
	}

}
