package net.hycrafthd.corelib.registry;

import net.minecraftforge.common.AchievementPage;

public class AchievementPageRegistry {

	public static void register(AchievementPage page) {
		AchievementPage.registerAchievementPage(page);
	}

}
