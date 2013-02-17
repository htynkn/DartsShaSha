package com.cnblogs.htynkn.extension;

import com.badlogic.gdx.Gdx;

public class DesktopStatisticsService implements IStatisticsService{

	@Override
	public void onResume() {
		Gdx.app.log("StatisticsService", "onResume");
	}

	@Override
	public void onPause() {
		Gdx.app.log("StatisticsService", "onPause");
	}

}
