package com.cnblogs.htynkn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.cnblogs.htynkn.screen.LoadingScreen;
import com.cnblogs.htynkn.screen.ShaScreen;

public class DartsGame extends Game {

	public static AssetManager manager;

	@Override
	public void create() {
		Screen screen = new LoadingScreen(this);
		this.setScreen(screen);
	}

	public static AssetManager getManager() {
		if (manager == null) {
			manager = new AssetManager();
		}
		return manager;
	}
}
