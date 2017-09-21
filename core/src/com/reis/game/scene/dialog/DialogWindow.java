package com.reis.game.scene.dialog;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.StringBuilder;
import com.reis.game.contants.GameConstants;
import com.reis.game.resource.ResourceManager;
import com.reis.game.scene.Scene;
import com.reis.game.scene.SceneManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardoreis on 9/5/17.
 */

public class DialogWindow extends Table {

    public static final int DIALOG_WINDOW_WIDTH = 400;
    public static final int DIALOG_WINDOW_HEIGHT = 100;
    private static final int DIALOG_EXTERNAL_TOP_PADDING = 5;
    private static final int DIALOG_EXTERNAL_LEFT_PADDING = 3;
    private static final int DIALOG_EXTERNAL_RIGHT_PADDING = 6;
    private static final int DIALOG_INTERNAL_PADDING = 10;


    private Table contentTable;
    private Label content;
    private List<CharSequence> lines = new ArrayList<CharSequence>();

    private int lastLineIndex = 0;

    public DialogWindow(String content) {
        this(content, null);
    }

    public DialogWindow(String content, DialogOptions options) {
        // TODO parse dialog options
        this.create(content);
    }

    private void create(String text) {
        Skin skin = ResourceManager.getDialogSkin();

        content = new Label(text, skin);
        content.setWrap(true);
        content.setAlignment(Align.top | Align.left);

        float availableWidth = calcAvailableWidth();
        float availableHeight = calcAvailableHeight();
        BitmapFont font = content.getStyle().font;
        lines = FontUtils.splitLines(font, text, lines, AutoWrap.WORDS, availableWidth);

        this.calcDialogPosition();
        this.setBackground(skin.getDrawable("background"));
        this.defaults().pad(
            DIALOG_EXTERNAL_TOP_PADDING,
            DIALOG_EXTERNAL_LEFT_PADDING,
            DIALOG_EXTERNAL_TOP_PADDING,
            DIALOG_EXTERNAL_RIGHT_PADDING
        );

        contentTable = new Table(skin);
        contentTable.defaults().pad(10).space(0);
        add(contentTable).expand().fill();
        contentTable.add(content)
                .width(availableWidth)
                .height(availableHeight);
    }

    private void showNextScreen() {
        BitmapFont font = content.getStyle().font;
        String contentToDisplay = getNextLines(font, 0);
        content.setText(contentToDisplay);

        this.invalidateHierarchy();
        this.invalidate();
        this.layout();
        this.pack();
    }

    private String getNextLines(BitmapFont font, float leading) {
        final float lineHeight = font.getLineHeight();
        float totalHeight = 0;
        int counter = lastLineIndex;
        for(int i = lastLineIndex; i < this.lines.size(); i++) {
            if (!availableSpaceForNextLine(font, leading, totalHeight))
                break;
            if (i == 0)
                totalHeight += lineHeight;
            else
                totalHeight += lineHeight + leading;
            counter++;
        }
        List<CharSequence> linesToShow = new ArrayList<CharSequence>(this.lines.subList(lastLineIndex, counter));
        StringBuilder contentToShow = new StringBuilder();
        for (CharSequence line : linesToShow) {
            contentToShow.append(line);
        }
        this.lastLineIndex += linesToShow.size();
        return contentToShow.toString();
    }

    private boolean availableSpaceForNextLine(BitmapFont font, float leading, float totalHeight) {
        return totalHeight + font.getLineHeight()
                + leading <= calcAvailableHeight();
    }

    private void calcDialogPosition() {
        float x = (GameConstants.WIDTH - DIALOG_WINDOW_WIDTH) * 0.5f;
        this.setPosition(x, 0);
    }

    private float calcAvailableWidth() {
        return DIALOG_WINDOW_WIDTH - 2 * DIALOG_INTERNAL_PADDING;
    }

    private float calcAvailableHeight() {
        return DIALOG_WINDOW_HEIGHT - 2 * DIALOG_INTERNAL_PADDING;
    }

    private boolean hasMoreScreens() {
        return this.lastLineIndex < this.lines.size() - 1;
    }

    public void show() {
        showNextScreen();
        Scene scene = SceneManager.getCurrentScene();
        Stage stage = scene.getStage();
        stage.addActor(this);
    }

    public boolean update() {
        boolean hasMoreScreens = hasMoreScreens();
        if (hasMoreScreens) {
            showNextScreen();
        } else {
            this.hide();
        }
        return !hasMoreScreens;
    }

    private void hide() {
        this.remove();
    }
}
