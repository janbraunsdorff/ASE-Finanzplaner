package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


class GoToAccountFromBankTest {
    @Test
    public void build(){
        var act = new GoToAccountFromBank();
        var command = new ExpertCommand("cd id", 2);
        var res = act.build(new State(Hierarchy.BANK, null, null), command);

        assertThat(res.command().getInput(), Matchers.is("account all -a id"));
        assertThat(res.transition(), Matchers.is(StateTransition.DEEPER));
        assertThat(res.ident(), Matchers.is("id"));

    }

    @Test
    public void buildMissingId(){
        var act = new GoToAccountFromBank();
        var command = new ExpertCommand("cd", 2);
        var res = act.build(new State(Hierarchy.BANK, null, null), command);

        assertThat(res.command().getInput(), Matchers.is("bank"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
        assertThat(res.ident(), Matchers.nullValue());

    }
}
