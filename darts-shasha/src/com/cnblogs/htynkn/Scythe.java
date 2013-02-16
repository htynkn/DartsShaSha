package com.cnblogs.htynkn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Scythe extends Actor {
	TextureRegion[] walkFrames; // 保存每一帧
	Animation animation; // 动画类
	float stateTime; // 总时间
	TextureRegion currentFrame; // 当前帧
	int titleWidth = 50; // 声明块宽度
	int titleHeight = 48; // 声明块高度
	int margin = 2; // 血条和人物之间的间隔
	int pixHeight = 5; // 血条高度
	int maxHp; // 总血量
	int currentHp; // 当前血量

	public Scythe(AtlasRegion atlasRegion) {
		super();
		this.setWidth(titleWidth); // 设置宽度
		this.setHeight(titleHeight + margin + pixHeight); // 设置高度
		this.maxHp = 2; // 设置总血量为1
		this.currentHp = 2; // 设置当前血量为1
		TextureRegion[][] temp = atlasRegion.split(titleWidth, titleHeight); // 分割图块
		walkFrames = new TextureRegion[4]; // 获取第二行的4帧
		for (int i = 0; i < 4; i++) {
			walkFrames[i] = temp[1][i];
		}
		animation = new Animation(0.1f, walkFrames); // 创建动画，帧间隔0.1
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		stateTime += Gdx.graphics.getDeltaTime(); // 获取总时间
		currentFrame = animation.getKeyFrame(stateTime, true); // 获取当前关键帧
		batch.draw(currentFrame, this.getX(), this.getY(), this.titleWidth,
				this.titleHeight); // 绘制
		Pixmap pixmap = new Pixmap(64, 8, Format.RGBA8888); // 生成一张64*8的图片
		pixmap.setColor(Color.BLACK); // 设置颜色为黑色
		pixmap.drawRectangle(0, 0, titleWidth, pixHeight); // 绘制边框
		pixmap.setColor(Color.RED); // 设置颜色为红色
		pixmap.fillRectangle(0, 1, titleWidth * currentHp / maxHp,
				pixHeight - 2); // 绘制血条
		Texture pixmaptex = new Texture(pixmap); // 生成图片
		TextureRegion pix = new TextureRegion(pixmaptex, titleWidth, pixHeight); // 切割图片
		batch.draw(pix, this.getX(), this.getY() + this.titleHeight
				+ this.margin, this.titleWidth, this.pixHeight); // 绘制
		pixmap.dispose();
	}

	public void beAttacked(int damage) {
		if (this.currentHp > damage) { // 如果血量大于伤害就扣除响应数值
			currentHp = currentHp - damage;
		} else if (this.currentHp > 0) { // 如果血量小于伤害但是仍有血量就让血量归零
			currentHp = 0;
		}
	}

	public Boolean isAlive() {
		return this.currentHp > 0;
	}
}
