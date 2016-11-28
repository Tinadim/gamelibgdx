package com.reis.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.reis.game.application.GameManager;
import com.reis.game.contants.SceneConstants;
import com.reis.game.resource.ResourceManager;
import com.reis.game.util.SceneUtils;

/**
 * Created by bernardoreis on 11/16/16.
 */

public class SplashScreen extends Scene implements SceneConstants {

    private static final int NEW_GAME = 0;
    private static final int LOAD_GAME = 1;

    private Texture texture = new Texture("gfx/splashBackground.jpg");
    private BitmapFont fontBig = ResourceManager.getSplashScreenFontBig();
    private BitmapFont fontSmall = ResourceManager.getSplashScreenFontSmall();


    public SplashScreen() {
        this.stage = new Stage();
        this.backgroundSong = ResourceManager.getSong("intro.mp3");
        Gdx.input.setInputProcessor(stage);
        createBackground(stage);
        createLabels(stage);
    }

    private void createLabels(Stage stage) {
        createOptions(stage);
        createTrademark(stage);
    }

    private void createBackground(Stage stage) {
        Image background = new Image(texture);
        background.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(background);
    }

    private void createOptions(Stage stage) {
        LabelStyle optionTextStyle = new LabelStyle();
        optionTextStyle.font = fontBig;
        optionTextStyle.fontColor = Color.BLACK;

        Label newGameOption = new Label("New Game", optionTextStyle);
        Label continueOption = new Label("Continue", optionTextStyle);
        SceneUtils.centerLabelHorizontally(stage, newGameOption);
        SceneUtils.centerLabelHorizontally(stage, continueOption);

        float y = stage.getHeight() * .5f + 2 * DEFAULT_PADDING;
        newGameOption.setY(y);
        newGameOption.setAlignment(Align.center, Align.center);
        y = stage.getHeight() * .5f - 2 * DEFAULT_PADDING;
        continueOption.setY(y);
        continueOption.setAlignment(Align.center, Align.center);

        stage.addActor(newGameOption);
        stage.addActor(continueOption);
        addTouchListenerToOptions(newGameOption, continueOption);
        addBlinkingEffect(newGameOption);
    }

    private void createTrademark(Stage stage) {
        LabelStyle footerTextStyle = new LabelStyle();
        footerTextStyle.font = fontSmall;
        footerTextStyle.fontColor = Color.BLACK;

        Label trademark = new Label("Bernardo Reis", footerTextStyle);
        Label year = new Label("2016", footerTextStyle);
        SceneUtils.centerLabelHorizontally(stage, trademark);
        SceneUtils.centerLabelHorizontally(stage, year);

        float y = DEFAULT_PADDING;
        year.setY(y);
        year.setAlignment(Align.center, Align.center);
        y = year.getY() + year.getPrefHeight() + 5;
        trademark.setY(y);
        trademark.setAlignment(Align.center, Align.center);

        stage.addActor(trademark);
        stage.addActor(year);
    }

    private void addTouchListenerToOptions(Label newGameOption, Label continueOption) {
        newGameOption.setTouchable(Touchable.enabled);
        continueOption.setTouchable(Touchable.enabled);
        newGameOption.addListener(new OptionSelectedListener(NEW_GAME));
        continueOption.addListener(new OptionSelectedListener(LOAD_GAME));
    }

    private void addBlinkingEffect(Label label) {
        label.addAction(Actions.forever(Actions.sequence(Actions.fadeIn(1), Actions.fadeOut(1))));
    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
        fontBig.dispose();
        fontSmall.dispose();
    }

    private class OptionSelectedListener extends InputListener {

        private int option;

        OptionSelectedListener(int option) {
            this.option = option;
        }

        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            onUserChoiceMade(this.option);
            return true;
        }

        private void onUserChoiceMade(int choice) {
            if (choice == NEW_GAME)
                GameManager.createNewGame();
            else if (choice == LOAD_GAME)
                System.out.println("LOAD_GAME"); //GameManager.loadGame();
        }
    }
}
