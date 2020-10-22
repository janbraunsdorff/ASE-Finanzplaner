package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.OutputBuilder;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;
import de.janbraunsdorff.ase.tech.printer.part.NewLine;
import de.janbraunsdorff.ase.tech.printer.part.TableDivider;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.List;

public class BankAllResult implements Result {
    private final List<BankEntity> result;
    private final SentencePiece table = new TableDivider("|");
    private final SentencePiece newLine = new NewLine();
    private final OutputBuilder builder;

    private final int length;
    private final int lengthAcronym;

    public BankAllResult(List<BankEntity> result) {
        this.result = result;
        this.length = getLengthOfName();
        this.lengthAcronym = getLengthOfAcronym();
        this.builder = new OutputBuilder();
    }

    @Override
    public String print() {
        builder.addTableVerticalDivider()
                .addText(String.format("%-37s", "ID"))
                .addTableVerticalDivider()
                .addText(String.format("%-" + length + "s", "Name"))
                .addTableVerticalDivider()
                .addText(String.format("%-" + lengthAcronym + "s", "Abkürzung"))
                .addTableVerticalDivider()
                .addText(String.format("%-10s", "Accounts"))
                .addTableVerticalDivider()
                .addNewLine()
                .addTableHorizontal(37, length, lengthAcronym, 10)
                .addNewLine();

        this.result.forEach(this::print);

        return builder.build();
    }

    private void print(BankEntity r) {
        builder.addTableVerticalDivider()
                .addText(String.format("%-37s", r.getId()))
                .addTableVerticalDivider()
                .addText(String.format("%-" + length + "s", r.getName()))
                .addTableVerticalDivider()
                .addText(String.format("%-" + lengthAcronym + "s", r.getAcronym()))
                .addTableVerticalDivider()
                .addText(String.format("%-10s", r.getAccounts().size()))
                .addTableVerticalDivider()
                .addNewLine();

    }

    private int getLengthOfAcronym() {
        final int lengthAcronym;
        if (!this.result.isEmpty()) {
            lengthAcronym = Math.max(this.result
                    .stream()
                    .max((a, b) -> a.getAcronym().length() < b.getAcronym().length() ? 1 : 0)
                    .get()
                    .getAcronym()
                    .length() + 2, 10);
        } else {
            lengthAcronym = 10;
        }
        return lengthAcronym;
    }

    private int getLengthOfName() {
        final int length;
        if (!this.result.isEmpty()) {
            length = Math.max(this.result
                    .stream()
                    .max((a, b) -> a.getName().length() < b.getName().length() ? 1 : 0)
                    .get()
                    .getName()
                    .length() + 2, 10);

        } else {
            length = 10;
        }
        return length;
    }


}
