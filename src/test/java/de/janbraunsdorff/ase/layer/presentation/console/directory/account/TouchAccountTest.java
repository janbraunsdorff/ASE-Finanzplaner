package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;

class TouchAccountTest {

    @Test
    public void build(){
        var builder = new TouchAccount();
        State sate = new State(Hierarchy.ACCOUNT, "bank", null);
        ExpertCommand command = new ExpertCommand("touch -na name -nr number -ac accountAcronym -a bank", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("account add -na name -nr number -ac accountAcronym -a bank"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "touch -na name -nr number",
            "touch -na name -ac accountAcronym",
            "touch -nr number -ac accountAcronym",
    })
    public void buildWithMissingParameter(String cmd){
        var builder = new TouchAccount();
        State sate = new State(Hierarchy.ACCOUNT, "bank", null);
        ExpertCommand command = new ExpertCommand(cmd, 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("account"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }
}
