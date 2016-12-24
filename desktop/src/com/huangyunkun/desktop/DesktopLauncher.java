package com.huangyunkun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.huangyunkun.DartsGame;
import com.huangyunkun.desktop.extension.DesktopStatisticsService;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "DartsShaSha";
        cfg.width = 480;
        cfg.height = 320;
        DartsGame.setStatisticsService(new DesktopStatisticsService());
        new LwjglApplication(new DartsGame(), cfg);
    }
}
