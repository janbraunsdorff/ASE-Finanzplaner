package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.App;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface PdfPart {
    HtmlObject render() throws IOException;
    default HtmlObject getTemplate(String name) throws IOException {
        InputStream resource = App.class.getClassLoader().getResourceAsStream("html/templates/" + name);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
        String lines = reader.lines().collect(Collectors.joining("\n"));
        return new HtmlObject(lines);
    }

    default String formatValue(Integer value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("%s€", df.format(value / 100.0));
    }
}
