package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.IdAlreadyExitsException;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankNewResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BankAddActionTest {
    @Test
    public void bankCreateIfAllTagsArePresent() throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        ICrudBankTestImpl service = new ICrudBankTestImpl();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act("bank add -a acronym -n name");

        assertThat(act, Matchers.instanceOf(BankNewResult.class));
        assertThat(service.createBank.getAcronym(), is("acronym"));
        assertThat(service.createBank.getName(), is("name"));
    }

    @Test
    public void bankCreateIfTagAcronymIsMissing() throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        ICrudBankTestImpl service = new ICrudBankTestImpl();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act("bank add -n name");

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }

    @Test
    public void bankCreateIfTagNameIsMissing() throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        ICrudBankTestImpl service = new ICrudBankTestImpl();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act("bank add -n name");

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }

    @Test
    public void bankCreateIfValueOfTagNameIsMissing() throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        ICrudBankTestImpl service = new ICrudBankTestImpl();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act("bank add -n -a acronym");

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }

    @Test
    public void bankCreateIfValueOfAcronymIsMissing() throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        ICrudBankTestImpl service = new ICrudBankTestImpl();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act("bank add -n name -a");

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
