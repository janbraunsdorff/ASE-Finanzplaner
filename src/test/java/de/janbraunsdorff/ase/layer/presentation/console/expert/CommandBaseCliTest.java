package de.janbraunsdorff.ase.layer.presentation.console.expert;

import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Field;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.ExitAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountIOApplicationTestImplementation;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankApplicationTestImplementation;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionApplicationTestImplementation;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionGroupAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionToPdfAction;

class CommandBaseCliTest {

    private DistributorAction controller;
    private Map<String, Distributor> useCases;

    @BeforeEach
    public void init() throws NoSuchFieldException, IllegalAccessException {
        var bankApp = new BankApplicationTestImplementation();
        var accountApp = new AccountIOApplicationTestImplementation();
        var transactionApp = new TransactionApplicationTestImplementation();

        var cli =  new CommandBaseCli(bankApp, accountApp, transactionApp, null);

        Field f = cli.getClass().getDeclaredField("controller");
        f.setAccessible(true);
        this.controller = (DistributorAction) f.get(cli);

        f = this.controller.getClass().getDeclaredField("useCases");
        f.setAccessible(true);
        this.useCases = (Map<String, Distributor>) f.get(this.controller);
    }

    @Test
    public void canCreate(){
        assertThat(controller, Matchers.notNullValue());
        assertThat(useCases, Matchers.notNullValue());
    }

    @Test
    public void controllerHasDomain(){
        assertThat(useCases.keySet(), Matchers.containsInAnyOrder("bank", "account", "transaction", "exit", "contract"));
        assertThat(useCases.keySet().size(), Matchers.is(5));
    }


    @Test
    public void bankDistributor() throws NoSuchFieldException, IllegalAccessException {
        var useCase = (DistributorUseCase) useCases.get("bank");

        Field f = useCase.getClass().getDeclaredField("actions");
        f.setAccessible(true);
        var actions = (Map<String, UseCase>) f.get(useCase);

        assertThat(actions.keySet(), Matchers.containsInAnyOrder("all", "add", "delete"));
        assertThat(actions.keySet().size(), Matchers.is(3));
        assertThat(actions.get("all"), Matchers.instanceOf(BankAllAction.class));
        assertThat(actions.get("add"), Matchers.instanceOf(BankAddAction.class));
        assertThat(actions.get("delete"), Matchers.instanceOf(BankDeleteAction.class));
    }

    @Test
    public void accountDistributor() throws NoSuchFieldException, IllegalAccessException {
        var useCase = (DistributorUseCase) useCases.get("account");

        Field f = useCase.getClass().getDeclaredField("actions");
        f.setAccessible(true);
        var actions = (Map<String, UseCase>) f.get(useCase);

        assertThat(actions.keySet(), Matchers.containsInAnyOrder("all", "add", "delete"));
        assertThat(actions.keySet().size(), Matchers.is(3));
        assertThat(actions.get("all"), Matchers.instanceOf(AccountAllAction.class));
        assertThat(actions.get("add"), Matchers.instanceOf(AccountAddAction.class));
        assertThat(actions.get("delete"), Matchers.instanceOf(AccountDeleteAction.class));
    }

    @Test
    public void transactionsDistributor() throws NoSuchFieldException, IllegalAccessException {
        var useCase = (DistributorUseCase) useCases.get("transaction");

        Field f = useCase.getClass().getDeclaredField("actions");
        f.setAccessible(true);
        var actions = (Map<String, UseCase>) f.get(useCase);

        assertThat(actions.keySet(), Matchers.containsInAnyOrder("all", "add", "group", "delete", "print"));
        assertThat(actions.keySet().size(), Matchers.is(5));
        assertThat(actions.get("all"), Matchers.instanceOf(TransactionAllAction.class));
        assertThat(actions.get("add"), Matchers.instanceOf(TransactionAddAction.class));
        assertThat(actions.get("group"), Matchers.instanceOf(TransactionGroupAction.class));
        assertThat(actions.get("delete"), Matchers.instanceOf(TransactionDeleteAction.class));
        assertThat(actions.get("print"), Matchers.instanceOf(TransactionToPdfAction.class));
    }

    @Test
    public void exit() {
        assertThat(this.useCases.get("exit"), Matchers.instanceOf(ExitAction.class));
    }


}
