package de.janbraunsdorff.ase.layer.domain.reporting.pdf;


import de.janbraunsdorff.ase.App;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.chapter.PdfChapter;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.PdfPart;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class PdfDocument implements Document{
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String date;
    private final ArrayList<PdfChapter> chapters;
    private final String name;

    public PdfDocument(String name) {
        this.name = name;
        this.date = LocalDateTime.now().format(dtf);
        this.chapters = new ArrayList<>();
    }

    public void prependChapter(PdfChapter chapter){
        this.chapters.add(0, chapter);
    }

    public void appendChapter(PdfChapter chapter){
        if (chapter == null){
            return;
        }
        this.chapters.add(chapter);
    }

    public HtmlObject render() {
        HtmlObject template = getTemplate("document.html");
        template.replace("headline", name);
        template.replace("path", getPathToResources());
        template.replace("pages", HtmlObject.join(chapters.stream().map(PdfPart::render).collect(Collectors.toList())));
        template.replace("date-created", date);


        int i = 0;
        while (template.replaceFirst("pages", String.join("/", String.valueOf(i+1), "{{totalPage}}"))) {
            i++;
        }

        template.replace("totalPage", String.valueOf(i+1));
        return template;
    }

    @NotNull
    private String getPathToResources() {
        String fileName = "config.txt";
        URL path = App.class.getClassLoader().getResource(fileName);
        return path.getFile().replaceAll(fileName, "");
    }

    public Path saveTo(String name) throws IOException {
       return this.render().saveTo("/app/output/" + name + "-" + UUID.randomUUID().toString().substring(24));

    }
}
