package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;

class HelpTransactionTest {
    @Test
    public void build(){
        var act = new HelpTransaction();
        var res = act.build(null, null);

        assertThat(res.command().getInput(), Matchers.is("transaction"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }
}
