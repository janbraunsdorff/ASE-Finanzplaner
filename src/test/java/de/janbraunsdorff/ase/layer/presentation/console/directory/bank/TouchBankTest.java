package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class TouchBankTest {
    @Test
    public void build(){
        var act = new TouchBank();
        var command = new ExpertCommand("touch -n name -a acronym -t type", 0);
        OverlayCommand build = act.build(new State(Hierarchy.BANK, null, null), command);

        assertThat(build.command().getInput(), Matchers.is("bank add -n name -a acronym -t type"));
        assertThat(build.transition(), Matchers.is(StateTransition.STAY));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "touch -n name -t type",
            "touch -n name -a acronym",
            "touch -a acronym -t type",
    })
    public void buildMissingId(String cmd){
        var act = new TouchBank();
        var command = new ExpertCommand(cmd, 0);
        OverlayCommand build = act.build(new State(Hierarchy.BANK, null, null), command);

        assertThat(build.command().getInput(), Matchers.is("bank"));
        assertThat(build.transition(), Matchers.is(StateTransition.STAY));
    }
}
