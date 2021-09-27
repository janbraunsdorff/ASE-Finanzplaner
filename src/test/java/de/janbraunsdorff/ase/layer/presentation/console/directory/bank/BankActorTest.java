package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;


class BankActorTest {

    @Test
    public void act(){
        var actor = new BankActor();
        actor.addBuilder("trigger", new CalledCommandBuilder());

        var command = new ExpertCommand("trigger", 0);
        var state = new State(Hierarchy.BANK, null, null);

        OverlayCommand act = actor.act(state, command);

        assertThat(act.command().getInput(), Matchers.is("state: BANK command: trigger"));
        assertThat(act.transition(), Matchers.is(StateTransition.STAY));

    }

    @Test
    public void actDefault(){
        var actor = new BankActor();

        var command = new ExpertCommand("trigger", 0);
        var state = new State(Hierarchy.BANK, null, null);

        OverlayCommand act = actor.act(state, command);

        assertThat(act.command().getInput(), Matchers.is("bank"));
        assertThat(act.transition(), Matchers.is(StateTransition.STAY));

    }


    private static class CalledCommandBuilder implements CommandBuilder {
        public State state;
        public ExpertCommand command;

        @Override
        public OverlayCommand build(State state, ExpertCommand command) {
            this.state = state;
            this.command = command;
            return new OverlayCommand( new ExpertCommand("state: " + state.getHierarchy() + " command: " + command.getInput(), 0), StateTransition.STAY);
        }
    }
}
