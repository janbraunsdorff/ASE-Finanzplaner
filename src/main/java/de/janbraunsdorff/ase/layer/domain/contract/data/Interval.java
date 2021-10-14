package de.janbraunsdorff.ase.layer.domain.contract.data;

public enum Interval {
    Monthly(1.0),
    Weekly(0.25),
    TwoWeekly(0.5),
    Quarterly(3.0),
    HalfYearly(6.0),
    Yearly(12.0);

    public final Double factor;

    Interval(Double factor) {
        this.factor = factor;
    }

    public Double getFactor() {
        return factor;
    }
}
