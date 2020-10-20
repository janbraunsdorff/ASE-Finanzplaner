package de.janbraunsdorff.ase.userinterface.console.result;

import de.janbraunsdorff.ase.tech.printer.SentencePiece;

import java.util.List;

public interface Result {
    default String getDivider(int counter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < counter; i++){
            builder.append("-");
        }
        return builder.toString();
    }

    List<SentencePiece> print();
}
