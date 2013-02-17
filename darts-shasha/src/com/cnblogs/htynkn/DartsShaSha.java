package com.cnblogs.htynkn;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.cnblogs.htynkn.controller.DartsController;
import com.cnblogs.htynkn.controller.TargetController;
import com.cnblogs.htynkn.listener.DartsListener;

public class DartsShaSha implements ApplicationListener {

	Stage stage;
	TextureAtlas atlas;
	Image man;
	TargetController targetController;
	DartsController dartsController;

	@Override
	public void create() {
		stage = new Stage(480, 320, true);

		LabelStyle labelStyle = new LabelStyle(new BitmapFont(), Color.BLACK); // 创建一个Label样式，使用默认黑色字体
		Label label = new Label("FPS:", labelStyle); // 创建标签，显示的文字是FPS：
		label.setName("fpsLabel"); // 设置标签名称为fpsLabel
		label.setPosition(0, 0); // 设置X,Y为0，即显示在左下角
		stage.addActor(label); // 将标签添加到舞台

		atlas = new TextureAtlas("pack/default.pack"); // 获取图册
		man = new Image(atlas.findRegion("Player")); // 获取图册中的Player.png并创建image对象
		man.setName("player");
		man.setX(0);
		man.setY(160 - man.getHeight() / 2); // 设置Y值，以让图片在中间显示
		stage.addActor(man); // 将主角添加到舞台

		targetController = new TargetController(atlas.findRegion("scythe")); // 创建怪兽群
		stage.addActor(targetController); // 将怪兽添加到舞台

		dartsController = new DartsController(atlas.findRegion("Projectile"));
		dartsController.setName("dartsController");
		stage.addActor(dartsController); // 添加飞镖组到舞台

		InputMultiplexer multiplexer = new InputMultiplexer(); // 多输入接收器
		GestureDetector gestureDetector = new GestureDetector(
				new DartsListener(this.stage));
		multiplexer.addProcessor(gestureDetector); // 添加手势识别
		multiplexer.addProcessor(stage); // 添加舞台
		Gdx.input.setInputProcessor(multiplexer); // 设置多输入接收器为接收器
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();

		// fps标签处理
		Label label = (Label) stage.getRoot().findActor("fpsLabel"); // 获取名为fpsLabel的标签
		label.setText("FPS:" + Gdx.graphics.getFramesPerSecond());

		targetController.update(this.stage); // 调用update方法，处理怪兽的逻辑
		dartsController.update(this.stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}
