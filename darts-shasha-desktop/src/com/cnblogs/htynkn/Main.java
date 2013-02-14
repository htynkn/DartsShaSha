package com.cnblogs.htynkn;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "darts-shasha";
		cfg.useGL20 = false;
		int i = 0;
		if (i == 1) {
			cfg.width = 480;
			cfg.height = 320;
		} else {
			cfg.width = 800;
			cfg.height = 480;
		}
		new LwjglApplication(new DartsShaSha(), cfg);
	}
}
