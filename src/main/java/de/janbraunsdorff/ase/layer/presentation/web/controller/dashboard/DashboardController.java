package de.janbraunsdorff.ase.layer.presentation.web.controller.dashboard;

import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("dashboard")
public class DashboardController {

    private final BankApplication bankApplication;

    public DashboardController(BankApplication bankApplication){

        this.bankApplication = bankApplication;
    }

    @GetMapping("overview")
    public ResponseDashboardOverview overview() throws BankNotFoundException {
        Value total = new Value(0);
        Value account = new Value(0);
        Value deposit = new Value(0);
        Value bar = new Value(0);

        for (BankDTO bank: this.bankApplication.get()){
            switch (bank.getType()) {
                case None:
                    bar = bar.add(bank.getValue());
                    break;
                case Retail:
                    account = account.add(bank.getValue());
                    break;
                case Investment:
                    deposit = deposit.add(bank.getValue());
                    break;
            }

            total = total.add(bank.getValue());
        };

        return new ResponseDashboardOverview(
                total.getFormatted(),
                account.getFormatted(),
                deposit.getFormatted(),
                bar.getFormatted(),
                total.getValue(),
                deposit.getValue(),
                bar.getValue()
        );
    }
}
