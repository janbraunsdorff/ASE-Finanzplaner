package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.DataPoint;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

public class MonthlyCourse extends Course {

    public MonthlyCourse(List<TransactionDTO> transactions) {
        super(transactions);
    }

    protected List<DataPoint> createDataPoints(List<DataPoint> list, Value accountValue) {
        List<DataPoint> dataPoints = new ArrayList<>();
        int month = transactions.get(0).getDate().getMonth().getValue();
        int year = transactions.get(0).getDate().getYear();
        LocalDate start = LocalDate.of(year, month, 1);

        int index = 0;
        while (start.getMonth().getValue() == month){
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
