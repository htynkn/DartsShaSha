package com.huangyunkun.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
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
import var3d.net.center.VStage;

import static com.huangyunkun.config.ResourceName.*;

public class ShaScreen extends VStage {
    public ShaScreen(VGame game) {
        super(game);
    }

    private TextureAtlas atlas;
    private Image man;
    private TargetController targetController;
    private DartsController dartsController;
    private Music backgroundMusic;

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

        atlas = this.game.getAssetManager().get("pack/sha/default.pack", TextureAtlas.class);

        man = new Image(atlas.findRegion(PLAYER)); // 获取图册中的Player.png并创建image对象

        this.game.getUI(man).setName("player").setX(0).setY(160 - man.getHeight() / 2);

        this.addActor(man); // 将主角添加到舞台

        targetController = new TargetController(this.game.getAssetManager(), atlas.findRegion(SCYTHE)); // 创建怪兽群
        this.addActor(targetController); // 将怪兽添加到舞台

        dartsController = new DartsController(this.game.getAssetManager(), atlas.findRegion(PROJECTILE));
        dartsController.setName("dartsController");
        this.addActor(dartsController); // 添加飞镖组到舞台

        InputMultiplexer multiplexer = new InputMultiplexer(); // 多输入接收器
        DartsDetector gestureDetector = new DartsDetector(this, new DartsListener());
        multiplexer.addProcessor(gestureDetector); // 添加手势识别
        multiplexer.addProcessor(this); // 添加舞台
        Gdx.input.setInputProcessor(multiplexer); // 设置多输入接收器为接收器

        backgroundMusic = this.game.getAssetManager().get("audio/background.ogg", Music.class);
        backgroundMusic.setLooping(true); // 循环播放
        backgroundMusic.setVolume(0.4f); // 设置音量
        backgroundMusic.play(); // 播放
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
