package com.cnblogs.htynkn;

import com.badlogic.gdx.Gdx;
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

	public Scythe(AtlasRegion atlasRegion) {
		super();
		this.setWidth(titleWidth); // 设置高度
		this.setHeight(titleHeight); // 设置宽度
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
		batch.draw(currentFrame, this.getX(), this.getY(), this.getWidth(),
				this.getHeight()); // 绘制
	}
}
