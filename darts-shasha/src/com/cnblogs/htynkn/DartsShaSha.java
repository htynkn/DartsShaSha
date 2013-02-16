package com.cnblogs.htynkn;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.cnblogs.htynkn.controller.TargetController;

public class DartsShaSha extends InputAdapter implements ApplicationListener {

	Stage stage;
	Group projectiles;
	TextureAtlas atlas;
	Image man;
	TargetController targetController;

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

		projectiles = new Group();
		projectiles.setName("projectiles");
		stage.addActor(projectiles); // 添加飞镖组到舞台

		InputMultiplexer multiplexer = new InputMultiplexer(); // 多输入接收器
		multiplexer.addProcessor(this); // 添加自身作为接收
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

		targetController.update(this.stage); //调用update方法，处理怪兽的逻辑
		
		// 如果飞镖已经飞到则刪除
		Actor[] projectile = projectiles.getChildren().begin();
		for (int j = 0; j < projectiles.getChildren().size; j++) {
			Actor actor = projectile[j];
			if (!ProjectileFactory.checkAlive(actor)) {
				projectiles.removeActor(actor);
			}
		}
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

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (projectiles.getChildren().size >= 5) { // 限制飞镖的数量为5个
			return false;
		}
		Vector3 vector3 = new Vector3(screenX, screenY, 0);
		stage.getCamera().unproject(vector3); // 坐标转化
		if (vector3.x < man.getX() + 10) { // 如果触摸太靠近左侧就不响应
			return false;
		}
		projectiles.addActor(ProjectileFactory.createProjectile(
				atlas.findRegion("Projectile"), man, vector3)); // 添加新飞镖到飞镖组
		return true;
	}
}
