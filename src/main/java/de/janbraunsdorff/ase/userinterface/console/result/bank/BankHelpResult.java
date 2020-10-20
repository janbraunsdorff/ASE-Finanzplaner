package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.ArrayList;
import java.util.List;

public class BankHelpResult implements Result {
    @Override
    public List<SentencePiece> print() {
        SentencePiece newCommand = new SentencePiece(Color.CYAN, ">");
        return new ArrayList<SentencePiece>() {{
            add(new SentencePiece(Color.WHITE, "Hilfe (Bank):\n"));
            add(newCommand);
            add(new SentencePiece(Color.WHITE, String.format("%-30s\t%s\n", "bank all", "zeigt alle Banken an")));
            add(newCommand);
            add(new SentencePiece(Color.WHITE, String.format("%-30s\t%s\n", "bank add [name]", "legt eine neue Bank mit einem Name an")));
            add(newCommand);
            add(new SentencePiece(Color.WHITE, String.format("%-30s\t%s\n", "bank update [ID] [name]", "aktualiserit den Namen einer Bank")));
            add(new SentencePiece(Color.BASE, "\n"));
        }};
    }
}
