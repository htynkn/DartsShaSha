package com.cnblogs.htynkn;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ProjectileFactory {
	public static Image createProjectile(AtlasRegion region, Actor man,
			Vector3 target) {
		Image image = new Image(region);
		image.setX(man.getX() + man.getWidth() / 2);
		image.setY(man.getY() + man.getHeight() / 2);
		image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
		image.addAction(Actions.repeat(50, Actions.rotateBy(360, 0.5f))); // 设置飞镖的旋转
		float r = (target.y - image.getY()) / (target.x - image.getX()); //获取斜率
		float detY = r * 480; //获取Y的变动量
		image.addAction(Actions.moveTo(480 + image.getX(), detY + image.getY(),
				2f)); // 设置飞镖的移动
		return image;
	}

	public static Boolean checkAlive(Actor projectile) {
		if (projectile.getActions().size == 1) {
			return false;
		}
		return true;
	}

	public static Boolean attackAlive(Actor target, Actor projectile) {
		Rectangle rectangle = new Rectangle(target.getX(), target.getY(),
				target.getWidth(), target.getHeight()); // 创建一个矩形
		return rectangle.contains(
				projectile.getX() + projectile.getWidth() / 2,
				projectile.getY() + projectile.getHeight() / 2); // 判断是否在矩阵中，即是否击中
	}
}
