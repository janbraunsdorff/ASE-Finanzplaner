package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class AccountActorTest {

    @Test
    public void actCommand(){
        var act = new AccountActor();
        Command builder = new Command();
        act.addBuilder("trigger", builder);

        OverlayCommand res = act.act(new State(Hierarchy.ACCOUNT, "bank", ""), new ExpertCommand("trigger", 1));

        assertThat(builder.command, Matchers.notNullValue());
        assertThat(builder.state, Matchers.notNullValue());

    }

    @Test
    public void actChangeDirUp(){
        var act = new AccountActor();
        Command builder = new Command();
        act.addBuilder("cd ..", builder);

        OverlayCommand res = act.act(new State(Hierarchy.ACCOUNT, "bank", ""), new ExpertCommand("cd ..", 1));

        assertThat(builder.command, Matchers.notNullValue());
        assertThat(builder.state, Matchers.notNullValue());

    }

    @Test
    public void actChangeDirAction(){
        var act = new AccountActor();
        Command builder = new Command();

        OverlayCommand res = act.act(new State(Hierarchy.ACCOUNT, "bank", ""), new ExpertCommand("cd ..", 1));

        assertThat(res.command().getInput(), Matchers.is("account"));
    }

    @Test
    public void actDefault(){
        var act = new AccountActor();

        OverlayCommand res = act.act(new State(Hierarchy.ACCOUNT, "bank", ""), new ExpertCommand("not found", 1));

        assertThat(res.command().getInput(), Matchers.is("account"));
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
