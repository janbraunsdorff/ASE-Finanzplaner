package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MonthlyCourse implements PdfPart {
    private final List<TransactionDTO> transactions;

    public MonthlyCourse(List<TransactionDTO> transactions) {
        transactions.sort(Comparator.comparing(TransactionDTO::getDate));
        this.transactions = transactions;
    }

    private List<DataPoint> createDataPoints(List<DataPoint> list, int accountValue) {
        List<DataPoint> dataPoints = new ArrayList<>();
        int month = transactions.get(0).getDate().getMonth().getValue();
        int year = transactions.get(0).getDate().getYear();
        LocalDate start = LocalDate.of(year, month, 1);

        int index = 0;
        while (start.getMonth().getValue() == month){
            if (list.size() > index && createKey(start).equals(list.get(index).getName())) {
                accountValue += list.get(index).getValue();
                index++;
            }
            dataPoints.add(new DataPoint(createKey(start), accountValue));
            start = start.plusDays(1);
        }

        return dataPoints;
    }

    public List<DataPoint> getDataPoints(int accountValue) {
        List<DataPoint> group = groupTransactionsByDate(transactions);
        return createDataPoints(group, accountValue);
    }



    @NotNull
    private List<DataPoint> groupTransactionsByDate(List<TransactionDTO> transactions) {
        Map<String, Integer> groups = new HashMap<>();
        transactions.forEach(t -> {
            String key = createKey(t.getDate());
            if (groups.containsKey(key)){
                groups.put(key, groups.get(key) + t.getValue());
            }else{
                groups.put(key, t.getValue());
            }

        });

        List<DataPoint> list = new ArrayList<>();
        groups.forEach((k, v) -> list.add(new DataPoint(k, v)));
        list.sort(Comparator.comparing(DataPoint::getName));
        return list;
    }

    private String createKey(LocalDate t) {
        return String.format("%02d.%02d.%2d", t.getDayOfMonth(), t.getMonth().getValue(), t.getYear() % 100);
    }

    @Override
    public HtmlObject render() throws IOException {
        List<DataPoint> dataPoints = getDataPoints(0);
        return render(dataPoints, "Monatsübersicht");
    }

    protected HtmlObject render(List<DataPoint> dataPoints, String type) throws IOException{
        HtmlObject template = getTemplate("course.html");

        template.replace("id", UUID.randomUUID().toString());
        template.replace("values", dataPoints
                .stream()
                .map(DataPoint::getValue)
                .mapToDouble(t -> t/100.0)
                .boxed()
                .map(t -> String.format("%.2f", t).replace(',', '.'))
                .collect(Collectors.joining(", "))
        );

        template.replace("lbl", dataPoints
                .stream()
                .map(DataPoint::getName)
                .map(m -> m.substring(0, 5))
                .map(m -> "'" + m + "'")
                .collect(Collectors.joining(", "))
        );

        template.replace("headline", type);

        return template;
    }
}
