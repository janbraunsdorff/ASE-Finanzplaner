package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf;


import de.janbraunsdorff.ase.App;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter.PdfChapter;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PdfDocument implements Document{
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String date;
    private final ArrayList<PdfChapter> chapters;
    private final String name;

    public PdfDocument(String name) {
        //TODO: First page desing
        this.name = name;
        this.date = LocalDateTime.now().format(dtf);
        this.chapters = new ArrayList<>();
    }

    public void addChapter(PdfChapter chapter){
        this.chapters.add(chapter);
    }

    public HtmlObject render() throws IOException {
        HtmlObject template = getTemplate("document.html");
        template.replace("headline", name);

        String fileName = "config.txt";
        URL path = App.class.getClassLoader().getResource(fileName);
        String root = path.getFile().replaceAll(fileName, "");
        template.replace("path", root);

        List<HtmlObject> collect = chapters.stream().map(chapter -> {
            try {
                return chapter.render();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new HtmlObject("");
        }).collect(Collectors.toList());
        HtmlObject pages = HtmlObject.join(collect);
        template.replace("pages", pages);
        template.replace("date-created", date);

        int i = 0;
        while (template.replaceFirst("pages", String.join("/", String.valueOf(i+1), "{{totalPage}}"))) {
            i++;
        }

        template.replace("totalPage", String.valueOf(i+1));



        return template;
    }

    public URI saveTo(String name) throws IOException {
       return this.render().saveTo(name + " " + UUID.randomUUID().toString().substring(20));

    }
}
