package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part;

public final class DataPoint {
    private final String name;
    private final Integer value;

    public DataPoint(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getAbsolutValue() {
        return Math.abs(this.value);
    }

}
