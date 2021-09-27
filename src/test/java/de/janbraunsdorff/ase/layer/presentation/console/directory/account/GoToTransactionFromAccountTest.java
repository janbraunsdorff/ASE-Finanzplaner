package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class GoToTransactionFromAccountTest {
    @Test
    public void build(){
        var builder = new GoToTransactionFromAccount();
        State sate = new State(Hierarchy.ACCOUNT, "bank", "account");
        ExpertCommand command = new ExpertCommand("cat account", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("transaction all -a account"));
        assertThat(res.transition(), Matchers.is(StateTransition.DEEPER));
    }

    @Test
    public void buildMissingAccount(){
        var builder = new GoToTransactionFromAccount();
        State sate = new State(Hierarchy.ACCOUNT, "bank", "");
        ExpertCommand command = new ExpertCommand("cat", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("account"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }
}
