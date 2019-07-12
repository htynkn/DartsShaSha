package com.huangyunkun.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.huangyunkun.elements.Dart;
import com.huangyunkun.elements.Scythe;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import static com.huangyunkun.config.ResourceName.DARTS_CONTROLLER;

public class TargetController extends IController {

    private Sound great;

    @Override
    public void update(Stage stage) {
        DartsController dartsController = stage.getRoot().findActor(DARTS_CONTROLLER);
        Actor[] projectile = dartsController.getChildren().begin();
        Actor[] targets = this.getChildren().begin();
        for (int i = 0; i < dartsController.getChildren().size; i++) {
            Actor actor = projectile[i];
            for (int j = 0; j < this.getChildren().size; j++) {
                Scythe scythe = (Scythe) targets[j];
                Dart dart = (Dart) actor;
                if (this.attackAlive(scythe, dart)) {
                    scythe.beAttacked(dart.getPower());
                    dartsController.removeActor(actor);
                    if (!scythe.isAlive()) {
                        this.removeActor(scythe);
                        great.play();
                    }
                    break;
                }
            }
        }
    }

    private boolean attackAlive(Scythe scythe, Dart dart) {
        Rectangle rectangle = new Rectangle(scythe.getX(), scythe.getY(), scythe.getWidth(),
                scythe.getHeight()); // 创建一个矩形
        return rectangle
                .contains(dart.getX() + dart.getWidth() / 2, dart.getY() + dart.getHeight() / 2); // 判断是否在矩阵中，即是否击中
    }

    public TargetController(AssetManager assetManager, AtlasRegion region) {
        super();
        int minY = 0;
        int maxY = (int) (320 - region.getRegionHeight() / 4); // 最大的Y值
        int tempY = 0;
        for (int i = 0; i < 3; i++) {
            Scythe image = new Scythe(region);
            image.setX(480 - image.getWidth());
            // 开始判断Y值是否符合要求
            boolean flag;
            do {
                flag = false;
                tempY = MathUtils.random(minY, maxY); // 生成Y值

                Actor[] actors = this.getChildren().begin(); // 获取当前已有的怪兽对象
                for (int j = 0; j < this.getChildren().size; j++) {
                    Actor tempActor = actors[j];
                    if (tempY == tempActor.getY()) { // 如果Y值相等，比如重合，所以舍弃,重新生成
                        flag = true;
                        break;
                    } else if (tempY < tempActor.getY()) { // 如果生成的Y值小于当前怪兽的Y值，则判断生成的Y值加上高度后是否合适
                        if ((tempY + region.getRegionHeight() / 4) >= tempActor.getY()) {
                            flag = true;
                            break;
                        }
                    } else { // 如果生成的Y值大于当前怪兽的Y值，则判断当前怪兽的Y值加上高度后是否合适
                        if (tempY <= (tempActor.getY() + region.getRegionHeight() / 4)) {
                            flag = true;
                            break;
                        }
                    }
                }
            } while (flag);
            image.setY(tempY);
            this.AddMove(image, MathUtils.random(3f, 8f)); // 怪兽移动效果
            this.addActor(image); // 添加到组中
            great = assetManager.get("audio/great.ogg", Sound.class);
        }
    }

    public void AddMove(Actor actor, float time) {
        actor.addAction(Actions.moveTo(0, actor.getY(), time));
    }
}
