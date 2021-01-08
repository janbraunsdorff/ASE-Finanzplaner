package de.janbraunsdorff.ase;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {

    public static void main(String[] args) throws IOException, URISyntaxException {
        new ApplicationWeb().run(args);

/*
        new ApplicationConsoleBuilder()
                .intJsonRepo("./")
                //.intMemoryRepo()
                .initDefaultApp()
                .createOverlay()
                //.createCli()
                .run();


 */

    }

}
