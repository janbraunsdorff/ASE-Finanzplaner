package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class GoToBankFromAccountTest {
    @Test
    public void build() {
        var builder = new GoToBankFromAccount();
        State sate = new State(Hierarchy.ACCOUNT, "bank", null);
        ExpertCommand command = new ExpertCommand("cd ..", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("bank all"));
        assertThat(res.transition(), Matchers.is(StateTransition.UP));

    }
}
