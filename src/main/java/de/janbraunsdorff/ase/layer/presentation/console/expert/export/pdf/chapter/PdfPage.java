package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter;

import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.PdfPart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfPage {
    private final List<PdfPart> parts;

    public PdfPage(PdfPart... part) {
        this.parts = new ArrayList<>();
        this.parts.addAll(Arrays.asList(part));
    }

    public PdfPage(List<PdfPart> parts) {
        this.parts = parts;
    }

    public HtmlObject render() throws IOException {
        List<HtmlObject> objects = new ArrayList<>();
        for (PdfPart part : parts) {
            HtmlObject render = part.render();
            objects.add(render);
        }
        return HtmlObject.join(objects);
    }
}
