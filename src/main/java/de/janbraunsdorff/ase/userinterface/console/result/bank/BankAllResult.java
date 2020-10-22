package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;
import de.janbraunsdorff.ase.tech.printer.part.NewLine;
import de.janbraunsdorff.ase.tech.printer.part.TableDivider;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.ArrayList;
import java.util.List;

public class BankAllResult implements Result {
    List<BankEntity> result;

    public BankAllResult(List<BankEntity> result) {
        this.result = result;
    }

    @Override
    public List<SentencePiece> print() {
        final SentencePiece table = new TableDivider("|");
        final SentencePiece newLine = new NewLine();

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

        final int lengthAcronym;
        if (!this.result.isEmpty()) {
            lengthAcronym = Math.max(this.result
                    .stream()
                    .max((a, b) -> a.getAcronym().length() < b.getAcronym().length() ? 1 : 0)
                    .get()
                    .getAcronym()
                    .length() + 2, 10);
        }else {
            lengthAcronym = 10;
        }

        List<SentencePiece> pieces = new ArrayList<SentencePiece>() {{
            add(table);
            add(new SentencePiece(Color.WHITE, String.format("%-37s", "ID")));
            add(table);
            add(new SentencePiece(Color.WHITE, String.format("%-" + length + "s", "Name")));
            add(table);
            add(new SentencePiece(Color.WHITE, String.format("%-"+ lengthAcronym +"s", "Abkürzung")));
            add(table);
            add(new SentencePiece(Color.WHITE, String.format("%-10s", "Accounts")));
            add(table);
            add(newLine);
            add(new TableDivider(String.format("+%-37s+%-" + length + "s+%"+ lengthAcronym+"s+%-10s+\n", getDivider(37), getDivider(length), getDivider(lengthAcronym), getDivider(10))));
        }};

        this.result.forEach(r -> {
            pieces.add(table);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-37s", r.getId())));
            pieces.add(table);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-" + length + "s", r.getName())));
            pieces.add(table);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-" + lengthAcronym + "s", r.getAcronym())));
            pieces.add(table);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-10s", r.getAccounts().size())));
            pieces.add(table);
            pieces.add(newLine);

        });

        return pieces;
    }


}
