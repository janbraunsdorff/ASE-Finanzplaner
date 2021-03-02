package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ListTransactionTest {

    @Test
    public void buildNormal(){
        var builder = new ListTransaction();
        State sate = new State(Hierarchy.TRANSACTION, "bank", "account");
        ExpertCommand command = new ExpertCommand("ls", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("transaction all -a account"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }

    @Test
    public void buildWithMoreElements(){
        var builder = new ListTransaction();
        State sate = new State(Hierarchy.TRANSACTION, "bank", "account");
        ExpertCommand command = new ExpertCommand("ls -n 240", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("transaction all -a account -n 240"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }

    @Test
    public void buildWithId(){
        var builder = new ListTransaction();
        State sate = new State(Hierarchy.TRANSACTION, "bank", "account");
        ExpertCommand command = new ExpertCommand("ls -f", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("transaction all -a account -f"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }
}
