package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.group;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.DataPoint;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.PdfPart;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class IncomeExpenses implements PdfPart {
    private final Map<String, Integer> incomeCategory = new HashMap<>();
    private final Map<String, Integer> incomePerson = new HashMap<>();
    private final Map<String, Integer> expenseCategory = new HashMap<>();
    private final Map<String, Integer> expensePerson = new HashMap<>();


    public IncomeExpenses(List<TransactionDTO> transactions) {
        transactions.forEach(t -> {
            if (t.getValue() > 0){
                this.incomeCategory.put(t.getCategory(), 0);
                this.incomePerson.put(t.getThirdParty(), 0);
            }else if (t.getValue() < 0){
                this.expenseCategory.put(t.getCategory(), 0);
                this.expensePerson.put(t.getThirdParty(), 0);
            }
        });

        transactions.forEach(t -> {
            if (t.getValue() > 0){
                this.incomeCategory.put(t.getCategory(), this.incomeCategory.get(t.getCategory()) + t.getValue());
                this.incomePerson.put(t.getThirdParty(), this.incomePerson.get(t.getThirdParty()) + t.getValue());
            }else if (t.getValue() < 0){
                this.expenseCategory.put(t.getCategory(), this.expenseCategory.get(t.getCategory()) + t.getValue());
                this.expensePerson.put(t.getThirdParty(), this.expensePerson.get(t.getThirdParty()) + t.getValue());
            }
        });
    }

    private List<DataPoint> reduceMap(Map<String, Integer> map){
        final List<DataPoint> valuePerObjects = new ArrayList<>();
        map.forEach((k, v) -> valuePerObjects.add(new DataPoint(k, v)));
        valuePerObjects.sort(Comparator.comparing(DataPoint::getAbsolutValue).reversed());

        List<DataPoint> returnValues = valuePerObjects.subList(0, Math.min(4, valuePerObjects.size()));


        if (valuePerObjects.size() > 5) {
            Integer reduce = valuePerObjects.subList(5, valuePerObjects.size() - 1)
                    .stream()
                    .map(DataPoint::getValue)
                    .reduce(0, Integer::sum);

            returnValues.add(new DataPoint("Andere", reduce));
        }

        return returnValues;
    }

    @Override
    public HtmlObject render() throws IOException {
        HtmlObject template = getTemplate("incomeExpenses.html");
        template.replace("id1", UUID.randomUUID().toString());
        template.replace("id2", UUID.randomUUID().toString());
        template.replace("id3", UUID.randomUUID().toString());
        template.replace("id4", UUID.randomUUID().toString());

        template.replace("c1-exp-cat-lbl", getLabels(incomeCategory));
        template.replace("c1-exp-cat-val", getValues(incomeCategory));

        template.replace("c2-exp-cat-lbl", getLabels(incomePerson));
        template.replace("c2-exp-cat-val", getValues(incomePerson));

        template.replace("c3-exp-cat-lbl", getLabels(expenseCategory));
        template.replace("c3-exp-cat-val", getValues(expenseCategory));

        template.replace("c4-exp-cat-lbl", getLabels(expensePerson));
        template.replace("c4-exp-cat-val", getValues(expenseCategory));

        return template;

    }

    @NotNull
    private String getLabels(Map<String, Integer> expenseCategory) {
        return reduceMap(expenseCategory)
                .stream()
                .map(DataPoint::getName)
                .map(n -> "'" + n + "'")
                .collect(Collectors.joining(", "));
    }

    @NotNull
    private String getValues(Map<String, Integer> expenseCategory) {
        return reduceMap(expenseCategory)
                .stream()
                .map(DataPoint::getValue)
                .mapToDouble(t -> t / 100.0)
                .boxed()
                .map(t -> String.format("%.2f", t).replace(',', '.'))
                .collect(Collectors.joining(", "));
    }
}
