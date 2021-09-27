package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGroupDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.BarChart;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.charts.ChartData;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.TablePrinterInputFactory;

public class TransactionGroupResult implements Result {
    private final TablePrinterInputFactory builder;
    private final List<TransactionGroupDTO> transactions;
    private final BarChart chartValue;
    private final BarChart chartTotal;
    private int lastYear;

    public TransactionGroupResult(List<TransactionGroupDTO> transactions) {
        this.transactions = transactions;
        this.builder = new TablePrinterInputFactory();


        List<String> keyCollections = transactions.stream().limit(10)
                .map(r -> String.format("%2s", r.getMonth()).replace(' ', '0') + "." + r.getYear() % 1000)
                .collect(Collectors.toList());
        Collections.reverse(keyCollections);
        String[] keys = new String[keyCollections.size()];
        keys = keyCollections.toArray(keys);


        List<Integer> collect = transactions.stream()
                .limit(10)
                .map(TransactionGroupDTO::getTotal)
                .map(a -> a / 100)
                .map(a -> (a < 0) ? 0 : a)
                .collect(Collectors.toList());
        Collections.reverse(collect);
        Integer[] values =  new Integer[collect.size()];
        values = collect.toArray(values);


        List<Integer> totalCollection = transactions.stream()
                .limit(10)
                .map(TransactionGroupDTO::getValue)
                .map(a -> a / 100)
                .map(a -> (a < 0) ? 0 : a)
                .collect(Collectors.toList());
        Collections.reverse(totalCollection);
        Integer[] total =  new Integer[totalCollection.size()];
        total = totalCollection.toArray(total);



        this.chartValue = new BarChart(new ChartData(values, keys, "Gewinn Letzten 10 Monate"));
        this.chartTotal = new BarChart(new ChartData(total, keys, "Kontostand"));
    }

    @Override
    public PrinterInput print() {
        this.builder
                .addHeadline("Account: " + transactions.get(0).getAccount())
                .addTableHeader(7, "Monat")
                .addTableHeader(16, "Einnahmen")
                .addTableHeader(16, "davon Vertrag")
                .addTableHeader(16, "Ausgaben")
                .addTableHeader(16, "davon Vertrag")
                .addTableHeader(16, "Gewinn")
                .addTableHeader(16, "Kontostand")
                .finishFirstLine()
                .addHorizontalLine();

        this.transactions.forEach(this::print);
        this.builder.addNewLine();
        this.builder.addChart(this.chartValue);
        this.builder.addNewLine();
        this.builder.addChart(this.chartTotal);
        return builder.build();
    }

    private void print(TransactionGroupDTO r) {
        if(lastYear != 0 && this.lastYear != r.getYear()){
            this.builder.addNewLine();
        }
        this.lastYear = r.getYear();
        builder
                .addLine()
                .addEntry(String.format("%2s", r.getMonth()).replace(' ', '0') + "." + r.getYear())
                .addAmount(r.getIncome())
                .addAmount(r.getIncomeContract())
                .addAmount(r.getOutcome())
                .addAmount(r.getOutcomeContract())
                .addAmount(r.getTotal())
                .addAmount(r.getValue())
                .addNewLine();
    }

}
