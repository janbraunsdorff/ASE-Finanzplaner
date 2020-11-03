package de.janbraunsdorff.ase.layer.presentation.console.actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class ActionTest {

    @Test
    public void Test_ActionCanParseParameterNo(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second", 2);

        assertThat(res.size(), is(0));
    }

    @Test
    public void Test_ActionCanParseParameterOneParameterWithOneValue(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second -a parameter", 2);

        assertThat(res.size(), is(1));
        assertThat(res.get("-a"), is("parameter"));
    }

    @Test
    public void Test_ActionCanParseParameterOneParameterWithNoneValue(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second -a", 2);

        assertThat(res.size(), is(1));
        assertThat(res.get("-a"), is(""));
    }

    @Test
    public void Test_ActionCanParseParameterOneParameterWithTwoValue(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second -a para meter", 2);

        assertThat(res.size(), is(1));
        assertThat(res.get("-a"), is("para meter"));
    }

    @Test
    public void Test_ActionCanParseParameterTwoParameterWithOneValue(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second -a para -b meter", 2);

        assertThat(res.size(), is(2));
        assertThat(res.get("-a"), is("para"));
        assertThat(res.get("-b"), is("meter"));
    }

    @Test
    public void Test_ActionCanParseParameterTwoParameterWithTwoValue(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second -a para 1 -b meter 2", 2);

        assertThat(res.size(), is(2));
        assertThat(res.get("-a"), is("para 1"));
        assertThat(res.get("-b"), is("meter 2"));
    }

    @Test
    public void Test_ActionCanParseParameterTwoParameterWithZeroAndTwoValue(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second -a -b meter 2", 2);

        assertThat(res.size(), is(2));
        assertThat(res.get("-a"), is(""));
        assertThat(res.get("-b"), is("meter 2"));
    }

    @Test
    public void Test_ActionCanParseParameterOneParameterWithPostDashInValue(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second -a 5-f", 2);

        assertThat(res.size(), is(1));
        assertThat(res.get("-a"), is("5-f"));
    }

    @Test
    public void Test_ActionCanParseParameterOneParameterWithPreDashInValue(){
        Action action = command -> null;
        Map<String, String> res = action.parseCommand("first second -a -ft", 2);

        assertThat(res.size(), is(2));
        assertThat(res.get("-a"), is(""));
        assertThat(res.get("-ft"), is(""));
    }



    @Test
    public void Test_CheckIfParameterIsOneTagPresent(){
        Action action = command -> null;
        Map<String, String> tags = new HashMap<String, String>(){{
            put("-a", "A");
        }};

        boolean isPresent = action.areTagsPresent(tags, "-a");

        assertThat(isPresent, is(true));
    }

    @Test
    public void Test_CheckIfParameterIsOneTagPresentTooManyTagsInMap(){
        Action action = command -> null;
        Map<String, String> tags = new HashMap<String, String>(){{
            put("-a", "A");
            put("-b", "B");
        }};

        boolean isPresent = action.areTagsPresent(tags, "-a");

        assertThat(isPresent, is(true));
    }

    @Test
    public void Test_CheckIfOneParameterIsPresentOneIsMissing(){
        Action action = command -> null;
        Map<String, String> tags = new HashMap<String, String>(){{
            put("-a", "A");
        }};

        boolean isPresent = action.areTagsPresent(tags, "-a", "-b");

        assertThat(isPresent, is(false));
    }
}
