package de.janbraunsdorff.ase.layer.presentation.console.expert;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.HelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class DefaultDistributorTest {
    @Test
    public void defaultDistributorReturnsHelp(){
        DistributorDefault distributor = new DistributorDefault();
        Result distribute = distributor.distribute(new ExpertCommand("", 2));

        assertThat(distribute, Matchers.instanceOf(HelpResult.class));
        assertThat(distribute, Matchers.instanceOf(Result.class));
    }
}
