package de.janbraunsdorff.ase.layer.presentation.console.expert;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.HelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;

class DistributorActionTest {

    @Test
    public void missingAction() {
        var dist = new DistributorAction();
        var res = dist.answer(new ExpertCommand("missing action", 2));

        assertThat(res, Matchers.instanceOf(HelpResult.class));
    }

    @Test
    public void answer() {
        var dist = new DistributorAction();
        dist.addUseCase("test", new TestDistributorDefault());
        var res = dist.answer(new ExpertCommand("test action", 2));

        assertThat(res, Matchers.instanceOf(TestResult.class));
    }

    private static class TestDistributorDefault implements Distributor {
        @Override
        public Result distribute(ExpertCommand command) {
            return new TestResult();
        }
    }

    public record TestResult() implements Result {
        @Override
        public PrinterInput print() {
            return null;
        }
    }

}
