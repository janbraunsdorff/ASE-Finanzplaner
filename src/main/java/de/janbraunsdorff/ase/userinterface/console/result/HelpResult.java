package de.janbraunsdorff.ase.userinterface.console.result;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;

import java.util.ArrayList;
import java.util.List;

public class HelpResult implements  Result{

    @Override
    public String print() {
        new ArrayList<SentencePiece>(){{
            add(new SentencePiece(Color.WHITE, "Hilfe :\n"));
            add(new SentencePiece(Color.CYAN, ">"));
            add(new SentencePiece(Color.WHITE, String.format("%-30s\t%s\n", "bank", "alle optionen für Bank")));
            add(new SentencePiece(Color.CYAN, ">"));
            add(new SentencePiece(Color.WHITE, String.format("%-30s\t%s\n", "option", "alle Options um die Anwenung zu Konfigurieren")));
            add(new SentencePiece(Color.BASE, "\n"));
        }};
        return "";
    }
}
