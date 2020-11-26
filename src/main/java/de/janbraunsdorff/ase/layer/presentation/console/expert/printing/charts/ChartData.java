package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts;

import java.util.Arrays;

public class ChartData {
    private final int[] value;
    private final String[] key;
    private final String name;

    public ChartData(int[] value, String[] key, String name) {
        this.value = (value.length > 10) ? value : Arrays.copyOf(value, 10);
        this.key = (key.length > 10) ? key : Arrays.copyOf(key, 10);
        this.name = name;
    }

    public int[] getValue() {
        return value;
    }

    public String[] getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
