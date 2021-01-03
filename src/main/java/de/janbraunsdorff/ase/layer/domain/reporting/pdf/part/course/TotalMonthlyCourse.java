package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.DataPoint;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;

import java.util.*;

public class TotalMonthlyCourse  extends MonthlyCourse {
    private final Value startValue;


    public TotalMonthlyCourse(List<TransactionDTO> transactions, Value startValue) {
        super(transactions);

        this.startValue = startValue;
    }

    public List<DataPoint> getDataPoints() {
        return super.getDataPoints(startValue);
    }


    @Override
    public HtmlObject render() {
        List<DataPoint> dataPoints = getDataPoints(startValue);
        return render(dataPoints, "Gesamt");
    }
}
