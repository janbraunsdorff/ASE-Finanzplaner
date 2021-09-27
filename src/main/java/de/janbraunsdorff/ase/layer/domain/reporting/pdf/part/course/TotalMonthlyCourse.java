package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.DataPoint;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

public class TotalMonthlyCourse  extends MonthlyCourse {
    private final Value startValue;


    public TotalMonthlyCourse(List<TransactionDTO> transactions, Value startValue) {
        super(transactions);

        this.startValue = startValue;
    }

    @Override
    public HtmlObject render() {
        List<DataPoint> dataPoints = getDataPoints(startValue);
        return render(dataPoints, "Gesamt");
    }
}
