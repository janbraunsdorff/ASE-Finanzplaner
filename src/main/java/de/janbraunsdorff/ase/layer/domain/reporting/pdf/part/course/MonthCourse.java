package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.DataPoint;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

import java.time.LocalDate;
import java.util.*;

public class MonthCourse extends Course {
    private final LocalDate end;
    private LocalDate start;

    public MonthCourse(List<TransactionDTO> transactions, LocalDate start, LocalDate end) {
        super(transactions);
        this.start = start;
        this.end = end;
    }

    protected List<DataPoint> createDataPoints(List<DataPoint> list, Value accountValue) {
        List<DataPoint> dataPoints = new ArrayList<>();

        int index = 0;
        while (start.isBefore(end)){
            if (isADatapointForDatePresent(list, start, index)) {
                accountValue = accountValue.add(list.get(index).getValue());
                index++;
            }
            dataPoints.add(new DataPoint(createKey(start), accountValue));
            start = start.plusDays(1);
        }

        return dataPoints;
    }
}
