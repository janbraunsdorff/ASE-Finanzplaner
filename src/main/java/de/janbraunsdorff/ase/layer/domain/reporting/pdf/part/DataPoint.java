package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part;

import de.janbraunsdorff.ase.layer.domain.Value;

public final class DataPoint {
    private final String name;
    private final Value value;

    public DataPoint(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value.getValue();
    }

    public String getDecimalString(){
        return this.value.getDecimalString().replace(',', '.');
    }

    public Integer getAbsolutValue() {
        return Math.abs(this.value.getValue());
    }

}
