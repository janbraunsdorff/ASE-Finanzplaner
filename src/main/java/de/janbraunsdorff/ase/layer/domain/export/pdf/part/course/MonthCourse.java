package de.janbraunsdorff.ase.layer.domain.export.pdf.part.course;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.export.pdf.part.DataPoint;

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

    protected List<DataPoint> createDataPoints(List<DataPoint> list, int accountValue) {
        List<DataPoint> dataPoints = new ArrayList<>();

        int index = 0;
        while (start.isBefore(end)){
            if (isADatapointForDatePresent(list, start, index)) {
                accountValue += list.get(index).getValue();
                index++;
            }
            dataPoints.add(new DataPoint(createKey(start), accountValue));
            start = start.plusDays(1);
        }

        return dataPoints;
    }
}
