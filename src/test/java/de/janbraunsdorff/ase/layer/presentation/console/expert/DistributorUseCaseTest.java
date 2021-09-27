package de.janbraunsdorff.ase.layer.presentation.console.expert;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.ErrorResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;

class DistributorUseCaseTest {

    @Test
    public void distributeNoTopLevelPresent(){
        var dist =new DistributorUseCase(new TestHelpResult(), new TestDefaultAction());
        var res = dist.distribute(new ExpertCommand("", 2));

        assertThat(res, Matchers.instanceOf(TestHelpResult.class));
    }

    @Test
    public void distributeMissingAction(){
        var dist =new DistributorUseCase(new TestHelpResult(), new TestDefaultAction());
        var res = dist.distribute(new ExpertCommand("top-level second-level", 2));

        assertThat(res, Matchers.instanceOf(TestResult.class));
    }

    @Test
    public void distribute(){
        var dist =new DistributorUseCase(new TestHelpResult(), new TestDefaultAction());
        dist.addAction("second-level", new TestPresentAction());
        var res = dist.distribute(new ExpertCommand("top-level second-level", 2));

        assertThat(res, Matchers.instanceOf(TestPresentResult.class));
    }

    @Test
    public void distributeGetsAnExceptionWhileExecutingDistribution(){
        var dist =new DistributorUseCase(new TestHelpResult(), new TestDefaultAction());
        dist.addAction("second-level", new TestExceptionAction());
        var res = dist.distribute(new ExpertCommand("top-level second-level", 2));

        assertThat(res, Matchers.instanceOf(ErrorResult.class));
    }


    private class TestDefaultAction implements UseCase {

        @Override
        public Result act(ExpertCommand command) throws Exception {
            return new TestResult();
        }
    }

    private class TestResult implements Result {
        @Override
        public PrinterInput print() {
            return null;
        }
    }

    private class TestPresentAction implements UseCase {

        @Override
        public Result act(ExpertCommand command) throws Exception {
            return new TestPresentResult();
        }
    }

    private class TestPresentResult implements Result {
        @Override
        public PrinterInput print() {
            return null;
        }
    }

    private class TestHelpResult implements Result {
        @Override
        public PrinterInput print() {
            return null;
        }
    }

    private class TestExceptionAction implements UseCase {

        @Override
        public Result act(ExpertCommand command) throws Exception {
            throw new Exception("I'm broken");
        }
    }

}
