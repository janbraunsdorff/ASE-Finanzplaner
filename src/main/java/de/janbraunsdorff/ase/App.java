package de.janbraunsdorff.ase;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {

    public static void main(String[] args) throws IOException, URISyntaxException {
        /*
        System.out.println(App.class.getClassLoader().getResource("bank.json"));
        String path = "/Users/janbraunsdorff/ASE-Finanzplaner/build/reports/tests/test/index.html";
        URI uri = new File(path).toURI();
        System.out.println(uri.getPath());
        System.out.println(Desktop.getDesktop().isSupported(Desktop.Action.BROWSE));
        System.out.println(Desktop.getDesktop());
        Desktop.getDesktop().browse(uri);
         */


        new ApplicationConsoleBuilder()
                .intJsonRepo("./")
                //.intMemoryRepo()
                .initDefaultApp()
                .createOverlay()
                //.createCli()
                .run();
    }

}
