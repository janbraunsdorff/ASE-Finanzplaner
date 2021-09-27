package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;


class CatTransactionTest {
    @Test
    public void build() {
        var builder = new CatTransaction();
        State sate = new State(Hierarchy.ACCOUNT, "bank", null);
        ExpertCommand command = new ExpertCommand("cat account", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("transaction all -a account"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));

    }

    @Test
    public void buildWithCount() {
        var builder = new CatTransaction();
        State sate = new State(Hierarchy.ACCOUNT, "bank", null);
        ExpertCommand command = new ExpertCommand("cat account -n 100", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("transaction all -a account -n 100"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));

    }

    @Test
    public void buildMissingAccount() {
        var builder = new CatTransaction();
        State sate = new State(Hierarchy.ACCOUNT, "bank", null);
        ExpertCommand command = new ExpertCommand("cat", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("account"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));

    }
}
