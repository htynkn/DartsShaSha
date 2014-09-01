package com.huangyunkun.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BaseScreen extends ScreenAdapter {
    protected Game game;
    protected Stage stage;

    public BaseScreen(Game game) {
        this.game = game;
    }
}
