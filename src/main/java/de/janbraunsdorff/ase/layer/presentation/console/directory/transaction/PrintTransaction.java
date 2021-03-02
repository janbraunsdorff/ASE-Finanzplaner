package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrintTransaction implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        var cmd = "transaction";
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");

            String account = "-a " + state.accountIdent();
            String[] dates = command.getInput().split(" ");

            String start = " -s " + formatDate(dates[1]).format(dtf);
            String end = "";

            if (dates.length >= 3) {
                end = " -e " + lastOfMonth(dates[2]).format(dtf);
            } else {
                end = " -e " + lastOfMonth(dates[1]).format(dtf);
            }

            cmd = "transaction print " + account + start + end;
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {

        }
        return new OverlayCommand(new ExpertCommand(cmd, 2), StateTransition.STAY);

    }

    private LocalDate formatDate(String in){
        int year = Integer.parseInt(in) % 10_000;
        int month = Integer.parseInt(in) / 10_000;
        return LocalDate.of(year, month, 1);
    }

    private LocalDate lastOfMonth(String in){
        LocalDate date = formatDate(in);
        return LocalDate.of(date.getYear(), date.getMonthValue(), date.lengthOfMonth());
    }
}
