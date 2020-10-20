package de.janbraunsdorff.ase.tech.printer;

import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.List;
import java.util.stream.Collectors;

public class Printer {

    public void print(Result res){
        printList(res.print());
    }

    private void printList(List<SentencePiece> sentencePieces){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String s = sentencePieces
                .stream()
                .map(SentencePiece::getPiece)
                .collect(Collectors.joining(""));
        System.out.println(s);
        System.out.flush();
    }
}
