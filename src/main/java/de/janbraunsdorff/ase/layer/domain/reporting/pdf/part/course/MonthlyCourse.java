package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.DataPoint;

import java.time.LocalDate;
import java.util.*;

public class MonthlyCourse extends Course {

    public MonthlyCourse(List<TransactionDTO> transactions) {
        super(transactions);
    }

    protected List<DataPoint> createDataPoints(List<DataPoint> list, int accountValue) {
        List<DataPoint> dataPoints = new ArrayList<>();
        int month = transactions.get(0).getDate().getMonth().getValue();
        int year = transactions.get(0).getDate().getYear();
        LocalDate start = LocalDate.of(year, month, 1);

        int index = 0;
        while (start.getMonth().getValue() == month){
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
