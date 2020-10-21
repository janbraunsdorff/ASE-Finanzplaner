package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.part.NewLine;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;
import de.janbraunsdorff.ase.tech.printer.part.TableDivider;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.ArrayList;
import java.util.List;

public class BankAllResult implements Result {
    List<BankEntity> result;

    public  BankAllResult(List<BankEntity> result){
        this.result = result;
    }

    @Override
    public List<SentencePiece> print() {
        final SentencePiece table = new TableDivider("|");
        final SentencePiece newLine = new NewLine();

        List<SentencePiece> pieces = new ArrayList<SentencePiece>(){{
            add(table);
            add(new SentencePiece(Color.WHITE, String.format("%-37s", "ID")));
            add(table);
            add(new SentencePiece(Color.WHITE, String.format("%-24s", "Name")));
            add(table);
            add(new SentencePiece(Color.WHITE, String.format("%-10s", "Accounts")));
            add(table);
            add(newLine);
            add(new TableDivider(String.format("+%-37s+%-24s+%-10s+\n", getDivider(37), getDivider(24), getDivider(10))));
        }};

        this.result.forEach(r -> {
            pieces.add(table);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-37s", r.getId())));
            pieces.add(table);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-24s", r.getName())));
            pieces.add(table);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-10s", r.getAccounts().size())));
            pieces.add(table);
            pieces.add(newLine);

        });

        return pieces;
    }


}
