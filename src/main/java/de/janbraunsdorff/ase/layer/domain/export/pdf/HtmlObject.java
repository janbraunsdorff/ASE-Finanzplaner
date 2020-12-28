package de.janbraunsdorff.ase.layer.domain.export.pdf;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlObject {
    private String html;

    public HtmlObject(String html) {
        this.html = html;
    }

    public static HtmlObject join(List<HtmlObject> postingPages) {
       return new HtmlObject(postingPages
               .stream()
               .map(t -> t.html)
               .collect(Collectors.joining("\n"))
       );
    }


    public void replace(String key, HtmlObject value) {
        this.html = html.replaceAll("\\{\\{" + key + "}}", value.html);
    }

    public boolean replaceFirst(String key, String value){
        this.html = html.replaceFirst("\\{\\{" + key + "}}", value);
        return this.html.contains("{{" + key + "}}");
    }

    public void replace(String key, String value) {
        this.html = html.replaceAll("\\{\\{" + key + "}}", value);
    }

    public void replace(String key, List<HtmlObject> items) {
        this.html = html.replaceAll("\\{\\{" + key + "}}", items.
                stream()
                .map(h -> h.html)
                .collect(Collectors.joining("\n"))
        );
    }

    public Path saveTo(String name) throws IOException {
        Path path = Paths.get("./" + name + ".html");
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, html.getBytes());
        }
        return path;
    }
}
