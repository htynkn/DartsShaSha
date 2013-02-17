package com.cnblogs.htynkn;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cnblogs.htynkn.extension.AndroidStatisticsService;

public class MainActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;
		DartsGame dartsGame = new DartsGame();
		DartsGame.setStatisticsService(new AndroidStatisticsService(this));
		initialize(dartsGame, cfg);
	}
}