package de.janbraunsdorff.ase;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        new ApplicationConsoleBuilder()
                .intJsonRepo("./") //.intMemoryRepo()
                .initDefaultApp()
                .createOverlay()// .createCli()
                .run();
    }

}
