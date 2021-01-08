package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts;

public enum ComposedChartElements {
    FULL(String.format("%c%c%c%c%c%c%c", ' ', ' ', ChartElement.Block.getSymbol(), ChartElement.Block.getSymbol(), ChartElement.Block.getSymbol(), ' ', ' ')),
    EMPTY(String.format("%7s", "")),
    HALF(String.format("%c%c%c%c%c%c%c", ' ', ' ', ChartElement.HalfBlock.getSymbol(), ChartElement.HalfBlock.getSymbol(), ChartElement.HalfBlock.getSymbol(), ' ', ' ')),
    X(String.format("%s%s%s%s%s%s%s", ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.HorizontalDataPoint.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol(), ChartElement.Horizontal.getSymbol())),
    Y(String.format("%8s%-3s", "", ChartElement.Vertical.getSymbol()));


    private final String symbol;

    ComposedChartElements(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
