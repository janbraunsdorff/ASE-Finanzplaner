package de.janbraunsdorff.ase.tech.printer;

import de.janbraunsdorff.ase.userinterface.console.result.Result;

public class Printer {

    public void print(Result res){
        printList(res.print());
    }

    private void printList(String s){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(s);
        System.out.flush();
    }
}
