package com.cnblogs.htynkn.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.cnblogs.htynkn.DartsGame;

public class LoadingScreen implements Screen {

	Game game;
	Stage stage;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (DartsGame.getManager().update()) {
			game.setScreen(new ShaScreen());
		}
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		AssetManager manager = DartsGame.getManager();
		manager.load("audio/background.ogg", Music.class);
		manager.load("audio/bing.ogg", Sound.class);
		manager.load("audio/great.ogg", Sound.class);
		manager.load("pack/sha/default.pack", TextureAtlas.class);

		stage = new Stage(480, 320, true);
		BitmapFont font = new BitmapFont(
				Gdx.files.internal("pack/loading/font.fnt"), false);
		LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
		Label label = new Label("游戏加载中...", labelStyle);
		label.setPosition(160, 150);
		stage.addActor(label);
		Image image = new Image(
				new TextureAtlas(Gdx.files
						.internal("pack/loading/default.pack"))
						.findRegion("ninja_attack"));
		image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
		image.addAction(Actions.repeat(1000, Actions.rotateBy(360, 3f)));
		image.setPosition(100, 140);
		stage.addActor(image);
	}

	@Override
	public void hide() {
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
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public LoadingScreen(Game game) {
		this.game = game;
	}
}
