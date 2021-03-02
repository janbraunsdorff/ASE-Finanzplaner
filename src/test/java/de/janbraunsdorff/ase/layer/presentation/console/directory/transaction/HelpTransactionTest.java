package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelpTransactionTest {
    @Test
    public void build(){
        var act = new HelpTransaction();
        var res = act.build(null, null);

        assertThat(res.command().getInput(), Matchers.is("transaction"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }
}
