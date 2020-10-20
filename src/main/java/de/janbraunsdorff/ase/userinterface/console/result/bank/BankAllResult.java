package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;
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
        final SentencePiece dividerVertical = new SentencePiece(Color.CYAN, "|");
        List<SentencePiece> pieces = new ArrayList<SentencePiece>(){{
            add(dividerVertical);
            add(new SentencePiece(Color.WHITE, String.format("%-37s", "ID")));
            add(dividerVertical);
            add(new SentencePiece(Color.WHITE, String.format("%-24s", "Name")));
            add(dividerVertical);
            add(new SentencePiece(Color.WHITE, String.format("%-10s", "Accounts")));
            add(dividerVertical);
            add(new SentencePiece(Color.BASE, "\n"));
            add(new SentencePiece(Color.CYAN,  String.format("+%-37s+%-24s+%-10s+\n", getDivider(37), getDivider(24), getDivider(10))));
        }};

        this.result.forEach(r -> {
            pieces.add(dividerVertical);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-37s", r.getId())));
            pieces.add(dividerVertical);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-24s", r.getName())));
            pieces.add(dividerVertical);
            pieces.add(new SentencePiece(Color.WHITE, String.format("%-10s", r.getAccounts().size())));
            pieces.add(dividerVertical);
            pieces.add(new SentencePiece(Color.BASE, "\n"));

        });

        return pieces;
    }


}
