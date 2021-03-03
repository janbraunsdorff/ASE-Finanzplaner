package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.*;
import de.janbraunsdorff.ase.layer.presentation.console.directory.account.AccountActor;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class TransactionActorTest {
    @Test
    public void act(){
        var act = new TransactionActor();
        Command builder = new Command();
        act.addBuilder("trigger", builder);

        OverlayCommand res = act.act(new State(Hierarchy.TRANSACTION, "bank", "account"), new ExpertCommand("trigger", 1));

        assertThat(builder.command, Matchers.notNullValue());
        assertThat(builder.state, Matchers.notNullValue());
    }

    @Test
    public void actChangeDirUp(){
        var act = new TransactionActor();
        Command builder = new Command();
        act.addBuilder("cd ..", builder);

        OverlayCommand res = act.act(new State(Hierarchy.TRANSACTION, "bank", "account"), new ExpertCommand("cd ..", 1));

        assertThat(builder.command, Matchers.notNullValue());
        assertThat(builder.state, Matchers.notNullValue());

    }

    @Test
    public void actChangeDirAction(){
        var act = new TransactionActor();
        Command builder = new Command();

        OverlayCommand res = act.act(new State(Hierarchy.TRANSACTION, "bank", "account"), new ExpertCommand("cd ..", 1));

        assertThat(res.command().getInput(), Matchers.is("transaction"));
    }

    @Test
    public void actDefault(){
        var act = new TransactionActor();

        OverlayCommand res = act.act(new State(Hierarchy.TRANSACTION, "bank", "account"), new ExpertCommand("not found", 1));

        assertThat(res.command().getInput(), Matchers.is("transaction"));
    }


    private static class Command implements CommandBuilder {
        public State state;
        public ExpertCommand command;

        @Override
        public OverlayCommand build(State state, ExpertCommand command) {
            this.state = state;
            this.command = command;
            return new OverlayCommand(command, StateTransition.STAY, "");
        }
    }
}
