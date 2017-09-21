package com.reis.game.scene.dialog;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.reis.game.entity.GameEntity;
import com.reis.game.state.GameState;
import com.reis.game.util.CandidateResourceEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bernardoreis on 11/17/16.
 */

public final class DialogManager {

    private static Map<Integer, List<CandidateDialog>> dialogsForEntity = null;
    private static DialogWindow currentDialog = null;

    private DialogManager() {}

    // TODO properly implement this
    public static void loadDialogs(GameState gameState) {
        dialogsForEntity = loadMockDialogs();
    }

    public static void showDialog(String content) {
        currentDialog = new DialogWindow(content);
        currentDialog.show();
    }

    public static void showDialogForEntity(GameEntity entity) {
        String content = getDialogForEntity(entity);
        showDialog(content);
    }

    public static boolean updateCurrentDialog() {
        boolean ended = false;
        if (currentDialog != null) {
            ended = currentDialog.update();
            if (ended) currentDialog = null;
        }
        return ended;
    }

    public static String getDialogForEntity(GameEntity entity) {
        List<CandidateDialog> dialogs = dialogsForEntity.get(entity.getId());
        if (dialogs == null || dialogs.isEmpty())
            return null;
        CandidateDialog fittestChoice = CandidateResourceEvaluator.getFittestCandidate(dialogs);
        if (fittestChoice != null) {
            return fittestChoice.getContent();
        }
        return null;
    }

    private static HashMap<Integer, List<CandidateDialog>> loadMockDialogs() {
        HashMap<Integer, List<CandidateDialog>> dialogs = new HashMap<Integer, List<CandidateDialog>>();
        List<CandidateDialog> dialogsForEntity2 = new ArrayList<CandidateDialog>();
        List<CandidateDialog> dialogsForEntity3 = new ArrayList<CandidateDialog>();

        CandidateDialog dialog1 = new CandidateDialog(1, 1, 0, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec imperdiet nunc et rutrum maximus. Aenean lorem tortor, elementum vitae venenatis et, rutrum et nulla. Duis tempus est quis neque fringilla sodales. Maecenas ultrices interdum nunc, ac rutrum tortor. Nunc laoreet quam non lacinia accumsan. Nulla molestie mi sit amet risus mollis, ut molestie nunc lacinia. Vestibulum bibendum ante ut sem pulvinar, et feugiat purus hendrerit. Mauris tincidunt magna et suscipit scelerisque. Etiam viverra vestibulum diam, iaculis eleifend elit mollis sit amet. Sed dapibus eros in massa vestibulum, ut bibendum dui bibendum.");
        CandidateDialog dialog2 = new CandidateDialog(2, 1, 1, "Hello Adventurer. Here's a quest for you. Talk to NPC 2");
        CandidateDialog dialog3 = new CandidateDialog(3, 1, 2, "Well done! Quest step completed! Talk to NPC 3 one more time.");
        CandidateDialog dialog4 = new CandidateDialog(4, 1, 3, "Quest complete! Well done!");

        dialogsForEntity2.add(dialog1);
        dialogsForEntity2.add(dialog3);
        dialogsForEntity3.add(dialog2);
        dialogsForEntity3.add(dialog4);

        dialogs.put(2, dialogsForEntity2);
        dialogs.put(3, dialogsForEntity3);
        return dialogs;
    }
}
