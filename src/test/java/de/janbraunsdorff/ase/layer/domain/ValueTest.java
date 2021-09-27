package de.janbraunsdorff.ase.layer.domain;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValueTest {

    @ParameterizedTest
    @CsvSource(value = {"0:0,00", "10:0,10", "100:1,00", "-123:-1,23"}, delimiter = ':')
    public void getDecimal(int val, String exp){
        var value = new Value(val);
        assertThat(value.getDecimalString(), Matchers.is(exp));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0,00€", "10:0,10€", "100:1,00€", "-123:-1,23€"}, delimiter = ':')
    public void getFormatted(int val, String exp){
        var value = new Value(val);
        assertThat(value.getFormatted(), Matchers.is(exp));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:true", "1:true", "-1:false"}, delimiter = ':')
    public void isPositive(int val, boolean exp){
        var value = new Value(val);
        assertThat(value.isPositive(), Matchers.is(exp));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0:0", "2:3:5", "-3:-5:-8", "-5:8:3"}, delimiter = ':')
    public void addValue(int base, int add, int exp){
        var value = new Value(base).add(new Value(add));
        assertThat(value.getValue(), Matchers.is(exp));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0:0", "2:3:5", "-3:-5:-8", "-5:8:3"}, delimiter = ':')
    public void addInt(int base, int add, int exp){
        var value = new Value(base).add(add);
        assertThat(value.getValue(), Matchers.is(exp));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0", "-2:2", "2:-2"}, delimiter = ':')
    public void negate(int base,  int exp){
        var value = new Value(base).negated();
        assertThat(value.getValue(), Matchers.is(exp));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0:0", "2:3:5", "-3:-5:-8", "-5:8:3"}, delimiter = ':')
    public void combine(int base, int add, int exp){
        var value = Value.combine(new Value(base), new Value(add));
        assertThat(value.getValue(), Matchers.is(exp));
    }
}
