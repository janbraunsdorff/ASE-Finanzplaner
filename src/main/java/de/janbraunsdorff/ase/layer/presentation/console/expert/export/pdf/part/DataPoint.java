package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

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

}
