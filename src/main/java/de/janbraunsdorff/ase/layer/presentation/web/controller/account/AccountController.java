package de.janbraunsdorff.ase.layer.presentation.web.controller.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountDetailDTO;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetDetailQuery;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetQuery;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountApplication accountService;
    private final BankApplication bankService;

    public AccountController(AccountApplication accountService, BankApplication bankService) {
        this.accountService = accountService;
        this.bankService = bankService;
    }

    @GetMapping("overview")
    public List<ResponseBankOverview> overview() throws BankNotFoundException, AccountNotFoundException {
        var res = new ArrayList<ResponseBankOverview>();
        List<BankDTO> bankDTOS = this.bankService.get();
        for (BankDTO b : bankDTOS) {
            var accountDetails = new ArrayList<AccountDetailDTO>();
            this.accountService.getAccountsOfBank(new AccountGetQuery(b.acronym())).forEach(t -> {
                System.out.println(t.getAcronym());
                accountDetails.add(this.accountService.getAccountDetail(new AccountGetDetailQuery(t.getAcronym())));
            });

            accountDetails.sort((x, y) -> {
                String[] splitX = x.lastPosting().split("\\.");
                String[] splitY = y.lastPosting().split("\\.");

                return LocalDate.of(Integer.parseInt(splitX[2]), Integer.parseInt(splitX[1]), Integer.parseInt(splitX[0])).compareTo(
                LocalDate.of(Integer.parseInt(splitY[2]), Integer.parseInt(splitY[1]), Integer.parseInt(splitY[0])));
            });
            Collections.reverse(accountDetails);

            res.add(new ResponseBankOverview(
                    b.name(),
                    b.acronym(),
                    accountDetails.stream().map(ResponseAccountOverview::new).collect(Collectors.toList())
            ));
        }

        res.sort((x,y) ->{
            String[] splitX = x.getAccounts().get(0).getLastPosting().split("\\.");
            String[] splitY = y.getAccounts().get(0).getLastPosting().split("\\.");

            return LocalDate.of(Integer.parseInt(splitX[2]), Integer.parseInt(splitX[1]), Integer.parseInt(splitX[0])).compareTo(
                    LocalDate.of(Integer.parseInt(splitY[2]), Integer.parseInt(splitY[1]), Integer.parseInt(splitY[0])));
        });

        Collections.reverse(res);


        return res;
    }
}
