package com.cnblogs.htynkn.listener;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cnblogs.htynkn.controller.DartsController;
import com.cnblogs.htynkn.elements.Dart;

public class DartsListener extends GestureAdapter {

	Stage stage;
	Vector2 last;
	Boolean touched;

	public DartsListener(Stage stage) {
		this.stage = stage;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		touched = true;
		DartsController dartsController = (DartsController) stage.getRoot()
				.findActor("dartsController");
		if (dartsController.getChildren().size >= 5) { // 限制飞镖的数量为5个
			return false;
		}
		Vector3 vector3 = new Vector3(x, y, 0);
		stage.getCamera().unproject(vector3); // 坐标转化
		Actor man = stage.getRoot().findActor("player");
		if (vector3.x < man.getX() + 10) { // 如果触摸太靠近左侧就不响应
			return false;
		}
		Dart dart = dartsController.createDart();
		dart.setX(man.getX() + man.getWidth() / 2);
		dart.setY(man.getY() + man.getHeight() / 2);
		dart.setTarget(vector3.x, vector3.y);
		dartsController.AddDarts(dart);
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		DartsController dartsController = (DartsController) stage.getRoot()
				.findActor("dartsController");
		if (dartsController.getChildren().size >= 5) { // 限制飞镖的数量为5个
			return false;
		}
		Vector3 vector3 = new Vector3(x, y, 0);
		stage.getCamera().unproject(vector3); // 坐标转化
		Actor man = stage.getRoot().findActor("player");
		if (vector3.x < man.getX() + 10) { // 如果触摸太靠近左侧就不响应
			return false;
		}
		Dart dart = dartsController.createDart();
		dart.setX(man.getX() + man.getWidth() / 2);
		dart.setY(man.getY() + man.getHeight() / 2);
		dart.setTarget(vector3.x, vector3.y);
		dart.setPower(2); // 设置杀伤力为2
		dart.setColor(Color.RED); // 设置成红色
		dartsController.AddDarts(dart);
		return true;
	}
}
