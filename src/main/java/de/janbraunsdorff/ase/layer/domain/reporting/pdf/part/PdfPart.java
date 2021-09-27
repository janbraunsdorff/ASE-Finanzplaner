package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import de.janbraunsdorff.ase.App;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;

public interface PdfPart {
    HtmlObject render();

    default HtmlObject getTemplate(String name) {
        InputStream resource = App.class.getClassLoader().getResourceAsStream("html/templates/" + name);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
        String lines = reader.lines().collect(Collectors.joining("\n"));
        return new HtmlObject(lines);
    }

}
