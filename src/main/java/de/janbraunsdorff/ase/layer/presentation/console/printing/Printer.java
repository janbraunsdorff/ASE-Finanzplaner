package de.janbraunsdorff.ase.layer.presentation.console.printing;


import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class Printer {

    public void print(Result res) {
        printList(res.print().getStringToPrint());
    }

    private void printList(String s) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(s);
        System.out.flush();
    }
}
