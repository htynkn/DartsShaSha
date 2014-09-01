package com.huangyunkun.elements;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Dart extends Image {

    private Vector2 target;
    private int power;

    public Dart(TextureAtlas.AtlasRegion region) {
        super(region);
        power = 1; //默认杀伤力为1
        this.setOrigin(getWidth() / 2, getHeight() / 2);
        this.addAction(Actions.repeat(50, Actions.rotateBy(360, 0.5f)));
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setTarget(Vector2 target) {
        this.target = target;
    }

    public void setTarget(float x, float y) {
        this.target = new Vector2(x, y);
    }

    public Vector2 getTarget() {
        return target;
    }
}
