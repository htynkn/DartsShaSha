package com.huangyunkun.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import var3d.net.center.VGame;
import var3d.net.center.VLabel;

public class LoadingStage extends VStageAdapter {
    public LoadingStage(VGame game) {
        super(game);

        this.setBackground(Color.BLACK);

        VLabel label = this.game.getLabel("游戏加载中...").setColor(Color.WHITE).setPosition(160, 150).setColor(Color.WHITE)
                .getActor();
        this.addActor(label);

        Image image = new Image(new TextureAtlas(Gdx.files.internal("pack/loading/default.pack")).findRegion("ninja_attack"));

        this.game.getUI(image).setOrigin(image.getWidth() / 2, image.getHeight() / 2).setPosition(100, 140)
                .addAction(Actions.repeat(1000, Actions.rotateBy(360, 10f)));

        this.addActor(image);
    }
}
