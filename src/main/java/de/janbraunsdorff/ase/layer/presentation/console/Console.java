package de.janbraunsdorff.ase.layer.presentation.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private final DistributorAction controller;

    public Console(DistributorAction controller) {
        this.controller = controller;
    }

    public void run() throws IOException {
        System.out.println("Dein Planer steht zur benutzung bereit");

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
