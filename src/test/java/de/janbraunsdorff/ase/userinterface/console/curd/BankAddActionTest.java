package de.janbraunsdorff.ase.userinterface.console.curd;

import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankNewResult;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BankAddActionTest {

    @Test
    public void Test_BankAddActionWithNoNameReturnsBankHelp(){
        CrudBankUseCaseTest crudBank = new CrudBankUseCaseTest();
        BankAddAction action = new BankAddAction(crudBank);
        Result res = action.act("bank add");
        assertThat( res.getClass().getName(), is(BankHelpResult.class.getName()));
    }

    @Test
    public void Test_BankAddActionWithOnlySpaceReturnsBankHelp(){
        CrudBankUseCaseTest crudBank = new CrudBankUseCaseTest();
        BankAddAction action = new BankAddAction(crudBank);
        Result res = action.act("bank add   ");
        assertThat( res.getClass().getName(), is(BankHelpResult.class.getName()));
    }

    @Test
    public void Test_BankAddActionWithNoFlag(){
        CrudBankUseCaseTest crudBank = new CrudBankUseCaseTest();
        BankAddAction action = new BankAddAction(crudBank);
        Result res = action.act("bank add testbank");
        assertThat( res.getClass().getName(), is(BankHelpResult.class.getName()));
    }

    @Test
    public void Test_BankAddActionWithWrongFlagA(){
        CrudBankUseCaseTest crudBank = new CrudBankUseCaseTest();
        BankAddAction action = new BankAddAction(crudBank);
        Result res = action.act("bank add -a testbank");
        assertThat( res.getClass().getName(), is(BankHelpResult.class.getName()));
    }

    @Test
    public void Test_BankAddActionWithWrongFlagB(){
        CrudBankUseCaseTest crudBank = new CrudBankUseCaseTest();
        BankAddAction action = new BankAddAction(crudBank);
        Result res = action.act("bank add -b testbank");
        assertThat( res.getClass().getName(), is(BankHelpResult.class.getName()));
    }

    @Test
    public void Test_BankAddActionCommandWithOneWordName() {
        CrudBankUseCaseTest crudBank = new CrudBankUseCaseTest();
        BankAddAction action = new BankAddAction(crudBank);
        Result res = action.act("bank add -n testbank");

        assertThat(res.getClass().getName(), is(BankNewResult.class.getName()));
        assertThat(crudBank.name, is("testbank"));
    }

    @Test
    public void Test_BankAddActionCommandWithTwoWordsName() {
        CrudBankUseCaseTest crudBank = new CrudBankUseCaseTest();
        BankAddAction action = new BankAddAction(crudBank);
        Result res = action.act("bank add -n test bank");

        assertThat(res.getClass().getName(), is(BankNewResult.class.getName()));
        assertThat(crudBank.name, is("test bank"));
    }
}
