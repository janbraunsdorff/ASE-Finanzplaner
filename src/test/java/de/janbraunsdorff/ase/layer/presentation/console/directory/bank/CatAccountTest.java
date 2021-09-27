package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class CatAccountTest {
    @Test
    public void build(){
        CatAccount act = new CatAccount();
        var command = new ExpertCommand("cat id", 0);
        OverlayCommand build = act.build(new State(Hierarchy.BANK, null, null), command);

        assertThat(build.command().getInput(), Matchers.is("account all -a id"));
        assertThat(build.transition(), Matchers.is(StateTransition.STAY));
    }

    @Test
    public void buildMissingId(){
        CatAccount act = new CatAccount();
        var command = new ExpertCommand("cat", 0);
        OverlayCommand build = act.build(new State(Hierarchy.BANK, null, null), command);

        assertThat(build.command().getInput(), Matchers.is("account"));
        assertThat(build.transition(), Matchers.is(StateTransition.STAY));
    }
}
