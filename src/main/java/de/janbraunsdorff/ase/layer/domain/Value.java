package de.janbraunsdorff.ase.layer.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class Value {
    private final Integer value;

    public Value(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String getDecimalString(){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
        otherSymbols.setDecimalSeparator(',');
        return new DecimalFormat("#,##0.00", otherSymbols).format(this.value / 100.0);
    }

    public String getFormatted(){
        return getDecimalString() + "€";
    }

    public boolean isPositive(){
        return value >= 0;
    }

    public Value add(Integer value) {
        return new Value(this.value + value);
    }

    public Value add(Value value) {
        return new Value(this.value + value.getValue());
    }

    public Value negated() {
        return new Value(this.value * -1);
    }

    public static Value combine (Value v1, Value v2){
        return v1.add(v2);
    }
}
