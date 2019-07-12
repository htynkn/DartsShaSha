package com.huangyunkun.stage;

import com.badlogic.gdx.utils.Align;
import var3d.net.center.SLabel;
import var3d.net.center.VGame;

public class FinishStage extends VStageAdapter {
    public FinishStage(VGame game) {
        super(game);
    }

    @Override
    public void start() {
        SLabel result = this.game.getSLabel("游戏结束")
                .setFontScale(2)
                .setAlignment(Align.center).show(Align.center);
        this.addActor(result);

        this.addActor(this.game.getLabel("分数为" + this.getIntent().get("score"))
                .show(0.5f, 0.3f)
        );
    }
}
