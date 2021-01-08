package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts;

import java.util.Arrays;

public record ChartData(Integer[] value, String[] key, String name) {
    public ChartData(Integer[] value, String[] key, String name) {
        this.value = (value.length < 10) ? value : Arrays.copyOf(value, 10);
        this.key = (key.length < 10) ? key : Arrays.copyOf(key, 10);
        this.name = name;
    }
}
