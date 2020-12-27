package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class TotalMonthlyCourse  extends MonthlyCourse {
    private final int startValue;


    public TotalMonthlyCourse(List<TransactionDTO> transactions, int startValue) {
        super(transactions);

        this.startValue = startValue;
    }

    public List<DataPoint> getDataPoints() {
        return super.getDataPoints(startValue);
    }


    @Override
    public HtmlObject render() throws IOException {
        List<DataPoint> dataPoints = getDataPoints(startValue);
        return render(dataPoints, "Gesamt");
    }
}
