package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


class ActorFactoryTest {

    @Test
    public void addBuilder(){
        TestActor testActor = new TestActor();
        var factory = new ActorFactory<TestActor>(testActor);
        TestCommandBuilder builder = new TestCommandBuilder();
        ActorFactory<TestActor> trigger = factory.addBuilder("trigger", builder);

        assertThat(trigger, Matchers.instanceOf(ActorFactory.class));
        assertThat(testActor.trigger, Matchers.is("trigger"));
        assertThat(testActor.builder, Matchers.is(builder));
    }

    @Test
    public void build(){
        TestActor testActor = new TestActor();
        var factory = new ActorFactory<TestActor>(testActor);
        TestCommandBuilder builder = new TestCommandBuilder();
        ActorFactory<TestActor> trigger = factory.addBuilder("trigger", builder);

        assertThat(trigger.build(), Matchers.instanceOf(TestActor.class));
    }


    private static class TestActor implements Actor{

        public String trigger;
        public CommandBuilder builder;

        @Override
        public OverlayCommand act(State state, ExpertCommand command) {
            return null;
        }

        @Override
        public void addBuilder(String trigger, CommandBuilder builder) {
            this.trigger = trigger;
            this.builder = builder;
        }
    }

    private static class TestCommandBuilder implements CommandBuilder {

        @Override
        public OverlayCommand build(State state, ExpertCommand command) {
            return null;
        }
    }
}
