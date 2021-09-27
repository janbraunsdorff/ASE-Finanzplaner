package de.janbraunsdorff.ase.layer.domain.account;

import java.util.HashMap;
import java.util.List;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountCategorizeMonthCommand;
import de.janbraunsdorff.ase.layer.domain.account.command.BankCourseCommand;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDetailDTO;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountMonthDTO;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetDetailQuery;

public interface AccountAnalyticsApplication {
    List<Value> getCourse(BankCourseCommand command) throws BankNotFoundException, AccountNotFoundException;
    AccountMonthDTO getMonth(AccountCategorizeMonthCommand command);
    HashMap<String, String> getAcronymToNameMapping();
    AccountDetailDTO getAccountDetail(AccountGetDetailQuery query);
}
