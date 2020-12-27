package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.App;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

public interface PdfPart {
    HtmlObject render() throws IOException;
    default HtmlObject getTemplate(String name) throws IOException {
        URL resource = App.class.getClassLoader().getResource("html/templates/" + name);
        String path = Objects.requireNonNull(resource).getPath();
        List<String> lines = Files.readAllLines(Paths.get(path));
        String html = String.join(" ", lines);
        return new HtmlObject(html);
    }

    default String formatValue(Integer value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("%s€", df.format(value / 100.0));
    }
}
