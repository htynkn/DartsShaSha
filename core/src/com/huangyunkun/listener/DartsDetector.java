package com.huangyunkun.listener;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.huangyunkun.controller.DartsController;
import com.huangyunkun.elements.Dart;

public class DartsDetector extends GestureDetector {

    Stage stage;

    public DartsDetector(Stage stage, GestureDetector.GestureListener listener) {
        super(listener);
        this.stage = stage;
        this.setLongPressSeconds(1f); //设置长按的判断标准为1秒
    }

    @Override
    public boolean touchUp(float x, float y, int pointer, int button) {
        DartsController dartsController = (DartsController) stage.getRoot().findActor("dartsController");
        if (dartsController.getChildren().size >= 5) { // 限制飞镖的数量为5个
            return false;
        }
        Vector3 vector3 = new Vector3(x, y, 0);
        stage.getCamera().unproject(vector3); // 坐标转化
        Actor man = stage.getRoot().findActor("player");
        if (vector3.x < man.getX() + 10) { // 如果触摸太靠近左侧就不响应
            return super.touchUp(x, y, pointer, button);
        }
        Dart dart = dartsController.createDart();
        dart.setX(man.getX() + man.getWidth() / 2);
        dart.setY(man.getY() + man.getHeight() / 2);
        dart.setTarget(vector3.x, vector3.y);
        if (this.isLongPressed()) { // 如果是长按就变成红色飞镖
            dart.setPower(2); // 设置杀伤力为2
            dart.setColor(Color.RED); // 设置成红色
        }
        dartsController.AddDarts(dart);
        return super.touchUp(x, y, pointer, button);
    }
}