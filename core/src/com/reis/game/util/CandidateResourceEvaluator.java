package com.reis.game.util;

import java.util.List;

/**
 * Created by bernardoreis on 11/19/16.
 */

public class CandidateResourceEvaluator {

    @SuppressWarnings("unchecked")
    public static <T extends CandidateResource> T getFittestCandidate(List<? extends CandidateResource> candidates) {
        CandidateResource fittest = null;
        for(CandidateResource current : candidates) {
            if(!current.matchesRequirements())
                continue;
            if(fittest == null)
                fittest = current;
            else
                fittest = fittest.compareTo(current);
        }
        return (T) fittest;
    }
}
