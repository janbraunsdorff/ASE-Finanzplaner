package de.janbraunsdorff.ase.layer.presentation.web.controller.account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountAnalyticsApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetQuery;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.presentation.web.controller.account.data.AllAccountRequest;
import de.janbraunsdorff.ase.layer.presentation.web.controller.account.data.AllAccountResponse;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountIOApplication accountService;
    private final BankApplication bankService;
    private final AccountAnalyticsApplication analyticsApplication;

    public AccountController(AccountIOApplication accountService, BankApplication bankService, AccountAnalyticsApplication analyticsApplication) {
        this.accountService = accountService;
        this.bankService = bankService;
        this.analyticsApplication = analyticsApplication;
    }

    @PostMapping("/all")
    public List<AllAccountResponse> all(@RequestBody AllAccountRequest request) throws BankNotFoundException {
        List<AllAccountResponse> allAccounts = new ArrayList<>();
        bankService.get().forEach(b -> {
            try {
                allAccounts.addAll(
                        accountService.getAccountsOfBank(new AccountGetQuery(b.acronym()))
                            .stream()
                            .map(a -> new AllAccountResponse(a.getAcronym(), b.name(), a.getName(), "", a.getValue().getFormatted(), a.getValue().isPositive()))
                            .collect(Collectors.toList())
                );
            } catch (BankNotFoundException | AccountNotFoundException e) {
                e.printStackTrace();
            }
        });



        return allAccounts;
    }
}
