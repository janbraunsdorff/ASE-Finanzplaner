package de.janbraunsdorff.ase.layer.domain.export.pdf.part;

import de.janbraunsdorff.ase.App;
import de.janbraunsdorff.ase.layer.domain.export.pdf.HtmlObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.stream.Collectors;

public interface PdfPart {
    HtmlObject render();
    default HtmlObject getTemplate(String name) {
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
