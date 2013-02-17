package com.cnblogs.htynkn;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cnblogs.htynkn.extension.DesktopStatisticsService;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "飞镖杀杀";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		DartsGame.setStatisticsService(new DesktopStatisticsService());
		new LwjglApplication(new DartsGame(), cfg);
	}
}
