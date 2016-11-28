package com.reis.game.scene.dialog;

import com.reis.game.entity.GameEntity;
import com.reis.game.state.GameState;
import com.reis.game.util.CandidateResourceEvaluator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bernardoreis on 11/17/16.
 */

public final class DialogManager {

    private static Map<Integer, List<Dialog>> dialogsForEntity = null;

    private DialogManager() {}

    public static void loadDialogs(GameState gameState) {
        dialogsForEntity = new HashMap<Integer, List<Dialog>>();
    }

    public static String getDialogForEntity(GameEntity entity) {
        List<Dialog> dialogs = dialogsForEntity.get(entity.getId());
        if(dialogs == null || dialogs.isEmpty())
            return null;
        Dialog fittestChoice = CandidateResourceEvaluator.getFittestCandidate(dialogs);
        if(fittestChoice != null)
            fittestChoice.getContent();
        return null;
    }
}
