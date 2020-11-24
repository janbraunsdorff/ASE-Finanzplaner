package de.janbraunsdorff.ase.layer.presentation.console;

import de.janbraunsdorff.ase.layer.presentation.console.action.system.HelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class DefaultDistributorTest {
    @Test
    public void defaultDistributorReturnsHelp(){
        DistributorDefault distributor = new DistributorDefault();
        Result distribute = distributor.distribute("");

        assertThat(distribute, Matchers.instanceOf(HelpResult.class));
        assertThat(distribute, Matchers.instanceOf(Result.class));
    }
}
