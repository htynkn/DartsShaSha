package com.huangyunkun;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.huangyunkun.extension.IStatisticsService;
import com.huangyunkun.stage.LoadingStage;
import com.huangyunkun.stage.ShaScreen;
import var3d.net.center.VGame;
import var3d.net.center.VListener;

public class DartsGame extends VGame {
    public static AssetManager manager;
    public static IStatisticsService statisticsService;

    public DartsGame(VListener varListener) {
        super(varListener);
        this.setSize(480, 320);
    }

    @Override
    public void init() {
        this.load(Music.class, "audio/background.ogg");
        this.load(Sound.class, "audio/bing.ogg");
        this.load(Sound.class, "audio/great.ogg");
        this.load(TextureAtlas.class, "pack/sha/default.pack");

        this.setStageLoad(LoadingStage.class);

        this.setStage(ShaScreen.class);
    }

    public static AssetManager getManager() {
        if (manager == null) {
            manager = new AssetManager();
        }
        return manager;
    }

    public static IStatisticsService getStatisticsService() {
        return statisticsService;
    }

    public static void setStatisticsService(IStatisticsService statisticsService) {
        DartsGame.statisticsService = statisticsService;
    }
}
