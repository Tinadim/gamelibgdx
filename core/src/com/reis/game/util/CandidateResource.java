package com.reis.game.util;

/**
 * Created by bernardoreis on 11/17/16.
 */

public interface CandidateResource {

    public CandidateResource compareTo(CandidateResource otherResource);

    public boolean matchesRequirements();
}
