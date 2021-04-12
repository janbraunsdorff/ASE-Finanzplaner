package de.janbraunsdorff.ase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.Locale;

public class App {

    public static void main(String[] args) throws IOException, URISyntaxException {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);

        new ApplicationWeb().run(args);
    }

}
