package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class RemoveBankTest {
    @Test
    public void build(){
        var act = new RemoveBank();
        var command = new ExpertCommand("delete id", 0);
        OverlayCommand build = act.build(new State(Hierarchy.BANK, null, null), command);

        assertThat(build.command().getInput(), Matchers.is("bank delete -a id"));
        assertThat(build.transition(), Matchers.is(StateTransition.STAY));
    }

    @Test
    public void buildMissingId(){
        var act = new RemoveBank();
        var command = new ExpertCommand("cat", 0);
        OverlayCommand build = act.build(new State(Hierarchy.BANK, null, null), command);

        assertThat(build.command().getInput(), Matchers.is("bank"));
        assertThat(build.transition(), Matchers.is(StateTransition.STAY));
    }
}
