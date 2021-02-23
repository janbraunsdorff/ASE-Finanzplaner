package de.janbraunsdorff.ase.layer.presentation.console.expert.printing;


import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

public class Printer {

    public void print(Result res) {
        printList(res.print().output());
    }

    private void printList(String s) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(s);
        System.out.flush();
    }
}
