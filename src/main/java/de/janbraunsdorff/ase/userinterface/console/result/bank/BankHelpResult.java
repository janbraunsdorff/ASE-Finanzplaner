package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;
import de.janbraunsdorff.ase.tech.printer.part.Command;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.ArrayList;
import java.util.List;

public class BankHelpResult implements Result {
    @Override
    public List<SentencePiece> print() {
        final SentencePiece newCommand = new Command();
        final int commandSize = 50;
        return new ArrayList<SentencePiece>() {{
            add(new SentencePiece(Color.WHITE, "Hilfe (Bank):\n"));
            add(newCommand);
            add(new SentencePiece(Color.WHITE, String.format("%-"+commandSize+"s\t%s\n", "bank all", "zeigt alle Banken an")));
            add(newCommand);
            add(new SentencePiece(Color.WHITE, String.format("%-"+commandSize+"s\t%s\n", "bank add -n [name] -a [abkürzung]", "legt eine neue Bank mit einem Name und einer Abkürzung an")));
            add(newCommand);
            add(new SentencePiece(Color.WHITE, String.format("%-"+commandSize+"s\t%s\n", "bank update -id [ID] -n [name]", "aktualiserit den Namen einer Bank")));
            add(new SentencePiece(Color.BASE, "\n"));
        }};
    }
}
