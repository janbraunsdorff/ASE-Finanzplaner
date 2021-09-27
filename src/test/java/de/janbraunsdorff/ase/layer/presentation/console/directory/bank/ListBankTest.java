package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class ListBankTest {

    @Test
    public void build(){
        var state = new State(Hierarchy.BANK, null, null);
        var cmd = new ExpertCommand("ls", 0);
        var act = new ListBank();
        OverlayCommand res = act.build(state, cmd);

        assertThat(res.command().getInput(), Matchers.is("bank all"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }
}
