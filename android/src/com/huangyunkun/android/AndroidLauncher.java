package com.huangyunkun.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.huangyunkun.DartsGame;
import com.huangyunkun.android.extension.AndroidStatisticsService;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DartsGame.setStatisticsService(new AndroidStatisticsService());

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new DartsGame(), config);
    }
}
