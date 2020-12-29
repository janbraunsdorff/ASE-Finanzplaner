package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.DataPoint;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.PdfPart;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Course implements PdfPart {
    protected final List<TransactionDTO> transactions;

    protected Course(List<TransactionDTO> transactions) {
        this.transactions = transactions;
        transactions.sort(Comparator.comparing(TransactionDTO::getDate));

    }

    abstract List<DataPoint> createDataPoints(List<DataPoint> list, int accountValue);

    protected String createKey(LocalDate t) {
        return String.format("%02d.%02d.%2d", t.getDayOfMonth(), t.getMonth().getValue(), t.getYear() % 100);
    }

    @Override
    public HtmlObject render() {
        List<DataPoint> dataPoints = getDataPoints(0);
        return render(dataPoints, "Intervall Übersicht");
    }

    protected HtmlObject render(List<DataPoint> dataPoints, String type){
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

        list.sort((o1, o2) -> {
            List<String> list1 = Arrays.asList(o1.getName().split("\\."));
            Collections.reverse(list1);
            String a = String.join(".", list1);

            List<String> list2 = Arrays.asList(o2.getName().split("\\."));
            Collections.reverse(list2);
            String b = String.join(".", list2);

            return a.compareTo(b);
        });

        return list;
    }

    public List<DataPoint> getDataPoints(int accountValue) {
        List<DataPoint> group = groupTransactionsByDate(transactions);
        return createDataPoints(group, accountValue);
    }

    protected boolean isADatapointForDatePresent(List<DataPoint> list, LocalDate start, int index) {
        return list.size() > index && createKey(start).equals(list.get(index).getName());
    }
}
