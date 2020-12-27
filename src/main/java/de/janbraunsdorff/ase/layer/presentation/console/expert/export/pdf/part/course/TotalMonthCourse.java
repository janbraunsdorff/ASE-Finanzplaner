package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.course;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.DataPoint;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TotalMonthCourse extends MonthCourse {
    private final int startValue;


    public TotalMonthCourse(List<TransactionDTO> transactions, int startValue, LocalDate start, LocalDate end) {
        super(transactions, start, end);

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
