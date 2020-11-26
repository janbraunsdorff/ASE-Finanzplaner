package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts;

public enum ChartElement {
    VerticalDataPoint('╣'),
    Vertical('║'),
    Edge('╩'),
    HorizontalDataPoint('╦'),
    Horizontal('═'),
    Block('█'),
    HalfBlock('▄');
    private final char symbol;

    ChartElement(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
