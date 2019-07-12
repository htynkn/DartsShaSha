package com.huangyunkun.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.huangyunkun.controller.DartsController;
import com.huangyunkun.controller.TargetController;
import com.huangyunkun.elements.Scythe;
import com.huangyunkun.listener.DartsDetector;
import com.huangyunkun.listener.DartsListener;
import var3d.net.center.VGame;

import static com.huangyunkun.config.ResourceName.*;

public class ShaScreen extends VStageAdapter {
    public ShaScreen(VGame game) {
        super(game);
    }

    private Image man;
    private TargetController targetController;
    private DartsController dartsController;

    @Override
    public void draw() {
        super.draw();

        targetController.update(this); // 调用update方法，处理怪兽的逻辑
        dartsController.update(this); //调用update方法，处理飞镖的逻辑

        Actor[] targets = targetController.getChildren().begin();
        for (int i = 0; i < targetController.getChildren().size; i++) {
            Scythe scythe = (Scythe) targets[i];
            if (scythe.getX() < man.getX() + 10) {
                //游戏结束
            }
        }
    }

    @Override
    public void init() {
        setBackground(Color.WHITE);
        this.game.showFps();

        TextureAtlas atlas = this.game.getTextureAtlas("pack/sha/default.pack");

        man = new Image(atlas.findRegion(PLAYER)); // 获取图册中的Player.png并创建image对象

        this.game.getUI(man).setName("player").setX(0).setY(160 - man.getHeight() / 2);
        this.addActor(man); // 将主角添加到舞台

        targetController = this.game.getUI(TargetController.class, this.game.getAssetManager(), atlas.findRegion(SCYTHE)).getActor();
        this.addActor(targetController); // 将怪兽添加到舞台

        dartsController = this.game.getUI(DartsController.class, this.game.getAssetManager(), atlas.findRegion(PROJECTILE))
                .setName("dartsController")
                .getActor();
        this.addActor(dartsController); // 添加飞镖组到舞台

        InputMultiplexer multiplexer = new InputMultiplexer(); // 多输入接收器
        DartsDetector gestureDetector = new DartsDetector(this, new DartsListener());
        multiplexer.addProcessor(gestureDetector); // 添加手势识别
        multiplexer.addProcessor(this); // 添加舞台
        Gdx.input.setInputProcessor(multiplexer); // 设置多输入接收器为接收器

        this.game.playMusic("audio/background.ogg");
    }
}
