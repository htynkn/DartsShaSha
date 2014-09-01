package com.huangyunkun.controller;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class IController extends Group {
    public abstract void update(Stage stage);
}
