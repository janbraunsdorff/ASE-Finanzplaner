package de.janbraunsdorff.ase.layer.domain.reporting.pdf.chapter;

import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.PdfPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfPage {
    private final List<PdfPart> parts;

    public PdfPage(PdfPart... part) {
        this.parts = new ArrayList<>();
        this.parts.addAll(Arrays.asList(part));
    }

    public HtmlObject render() {
        List<HtmlObject> objects = new ArrayList<>();
        for (PdfPart part : parts) {
            HtmlObject render = part.render();
            objects.add(render);
        }
        return HtmlObject.join(objects);
    }
}
