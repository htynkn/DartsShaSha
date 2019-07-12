package com.huangyunkun.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import var3d.net.center.VGame;
import var3d.net.center.VLabel;
import var3d.net.center.VStage;

public class LoadingStage extends VStage {
    public LoadingStage(VGame game) {
        super(game);
    }

    @Override
    public void init() {
        setBackground(Color.WHITE);

        VLabel label = this.game.getLabel("游戏加载中...").setPosition(160, 150).setColor(Color.WHITE).getActor();
        this.addActor(label);

        Image image = new Image(
                new TextureAtlas(Gdx.files.internal("pack/loading/default.pack")).findRegion("ninja_attack"));

        this.game.getUI(image).setOrigin(image.getWidth() / 2, image.getHeight() / 2).setPosition(100, 140)
                .addAction(Actions.repeat(1000, Actions.rotateBy(360, 3f)));

        this.addActor(image);
    }

    @Override
    public void start() {

    }

    @Override
    public void reStart() {

    }

    @Override
    public void back() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void changing(float width, float height) {

    }
}
