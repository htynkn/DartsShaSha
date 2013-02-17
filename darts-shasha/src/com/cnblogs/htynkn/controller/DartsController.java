package com.cnblogs.htynkn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.cnblogs.htynkn.DartsGame;
import com.cnblogs.htynkn.elements.Dart;

public class DartsController extends IController {

	AtlasRegion region;
	Sound bing;

	@Override
	public void update(Stage stage) {
		// 如果飞镖已经飞到则刪除
		Actor[] projectile = this.getChildren().begin();
		for (int j = 0; j < this.getChildren().size; j++) {
			Actor actor = projectile[j];
			if (!this.checkAlive(actor)) {
				this.removeActor(actor);
			}
		}
	}

	public DartsController(AtlasRegion region) {
		this.region = region;
		this.bing = DartsGame.getManager().get("audio/bing.wav", Sound.class);
	}

	public void AddDarts(Dart dart) {
		if (this.getChildren().size >= 5) { // 如果飞镖数量大于等于5个就结束
			return;
		}
		float r = (dart.getTarget().y - dart.getY())
				/ (dart.getTarget().x - dart.getX()); // 获取斜率
		float detY = r * 480; // 获取Y的变动量
		dart.addAction(Actions.moveTo(480 + dart.getX(), detY + dart.getY(), 2f)); // 设置飞镖的移动
		this.addActor(dart);
		bing.play();
	}

	public Boolean attackAlive(Actor target, Actor projectile) {
		Rectangle rectangle = new Rectangle(target.getX(), target.getY(),
				target.getWidth(), target.getHeight()); // 创建一个矩形
		return rectangle.contains(
				projectile.getX() + projectile.getWidth() / 2,
				projectile.getY() + projectile.getHeight() / 2); // 判断是否在矩阵中，即是否击中
	}

	public Boolean checkAlive(Actor projectile) {
		if (projectile.getActions().size == 1) {
			return false;
		}
		return true;
	}

	public Dart createDart() {
		return new Dart(region);
	}
}
