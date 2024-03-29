package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course;

import java.time.LocalDate;
import java.util.List;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.DataPoint;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

public class TotalMonthCourse extends MonthCourse {
    private final Value startValue;


    public TotalMonthCourse(List<TransactionDTO> transactions, Value startValue, LocalDate start, LocalDate end) {
        super(transactions, start, end);

        this.startValue = startValue;
    }

    @Override
    public HtmlObject render() {
        List<DataPoint> dataPoints = getDataPoints(startValue);
        return render(dataPoints, "Gesamt");
    }
}
