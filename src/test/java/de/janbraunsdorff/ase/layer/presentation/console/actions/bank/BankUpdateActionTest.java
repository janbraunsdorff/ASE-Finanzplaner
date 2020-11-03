package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BankUpdateActionTest {

    @Test
    public void Test_UpdateNameOfBankAndAcronym(){
        CrudBankUseCaseTest crud = new CrudBankUseCaseTest();
        BankUpdateAction action = new BankUpdateAction(crud);

        action.act("bank update -i 123 -n updated bank -a ub");

        assertThat(crud.name, is("updated bank"));
        assertThat(crud.acronym, is("ub"));
        assertThat(crud.id, is("123"));

    }

    @Test
    public void Test_UpdateBankIdIsMissing(){
        CrudBankUseCaseTest crud = new CrudBankUseCaseTest();
        BankUpdateAction action = new BankUpdateAction(crud);

        Result res = action.act("bank update  -n updated bank -a ub");

        assertThat( res.getClass().getName(), is(BankHelpResult.class.getName()));
    }

    @Test
    public void Test_UpdateBankNameIsMissing(){
        CrudBankUseCaseTest crud = new CrudBankUseCaseTest();
        BankUpdateAction action = new BankUpdateAction(crud);

        Result res = action.act("bank update -i 123  -a ub");

        assertThat( res.getClass().getName(), is(BankHelpResult.class.getName()));
    }

    @Test
    public void Test_UpdateBankAcronymIsMissing(){
        CrudBankUseCaseTest crud = new CrudBankUseCaseTest();
        BankUpdateAction action = new BankUpdateAction(crud);

        Result res = action.act("bank update -i 123 -n name");

        assertThat( res.getClass().getName(), is(BankHelpResult.class.getName()));
    }
}
