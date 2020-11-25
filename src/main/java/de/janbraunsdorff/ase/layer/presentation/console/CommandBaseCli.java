package de.janbraunsdorff.ase.layer.presentation.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandBaseCli {
    private final DistributorAction controller;

    public CommandBaseCli(DistributorAction controller) {
        this.controller = controller;
    }

    public void run() throws IOException {
        //      TODO: cleaner reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name = reader.readLine();
            if (name == null) {
                System.exit(0);
            }
            controller.answer(name);
        }
    }
}
