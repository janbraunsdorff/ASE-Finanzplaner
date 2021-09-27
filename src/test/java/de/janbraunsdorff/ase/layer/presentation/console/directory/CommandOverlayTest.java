package de.janbraunsdorff.ase.layer.presentation.console.directory;


import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.account.CatTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.account.GoToBankFromAccount;
import de.janbraunsdorff.ase.layer.presentation.console.directory.account.GoToTransactionFromAccount;
import de.janbraunsdorff.ase.layer.presentation.console.directory.account.ListAccount;
import de.janbraunsdorff.ase.layer.presentation.console.directory.account.RemoveAccount;
import de.janbraunsdorff.ase.layer.presentation.console.directory.account.TouchAccount;
import de.janbraunsdorff.ase.layer.presentation.console.directory.bank.CatAccount;
import de.janbraunsdorff.ase.layer.presentation.console.directory.bank.GoToAccountFromBank;
import de.janbraunsdorff.ase.layer.presentation.console.directory.bank.ListBank;
import de.janbraunsdorff.ase.layer.presentation.console.directory.bank.RemoveBank;
import de.janbraunsdorff.ase.layer.presentation.console.directory.bank.TouchBank;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.DeleteTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.GoToAccountFromTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.GroupTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.HelpTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.ListTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.PrintTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.TouchTransaction;

class CommandOverlayTest {

    private CommandOverlay overlay;
    private Map<Hierarchy, Actor> actors;

    @BeforeEach
    public void init() throws IllegalAccessException, NoSuchFieldException {
        this.overlay = new CommandOverlay(null);

        Field f = overlay.getClass().getDeclaredField("actors");
        f.setAccessible(true);
        this.actors = (Map<Hierarchy, Actor>) f.get(overlay);
    }

    @Test
    public void createActors(){
        assertThat(this.actors.size(), Matchers.is(3));
        assertThat(this.actors.keySet(), Matchers.containsInAnyOrder(Hierarchy.BANK, Hierarchy.ACCOUNT, Hierarchy.TRANSACTION));
    }

    @Test
    public void testBankActor() throws NoSuchFieldException, IllegalAccessException {
        var bankActor = this.actors.get(Hierarchy.BANK);

        Field f = bankActor.getClass().getDeclaredField("builder");
        f.setAccessible(true);
        var builder = (HashMap<String, CommandBuilder>) f.get(bankActor);

        assertThat(builder.size(), Matchers.is(5));
        assertThat(builder.keySet(), Matchers.containsInAnyOrder("ls", "cd", "cat", "touch", "rm"));
        assertThat(builder.get("ls"), Matchers.instanceOf(ListBank.class));
        assertThat(builder.get("cd"), Matchers.instanceOf(GoToAccountFromBank.class));
        assertThat(builder.get("cat"), Matchers.instanceOf(CatAccount.class));
        assertThat(builder.get("touch"), Matchers.instanceOf(TouchBank.class));
        assertThat(builder.get("rm"), Matchers.instanceOf(RemoveBank.class));
    }

    @Test
    public void testAccountActor() throws NoSuchFieldException, IllegalAccessException {
        var bankActor = this.actors.get(Hierarchy.ACCOUNT);

        Field f = bankActor.getClass().getDeclaredField("builder");
        f.setAccessible(true);
        var builder = (HashMap<String, CommandBuilder>) f.get(bankActor);

        assertThat(builder.size(), Matchers.is(6));
        assertThat(builder.keySet(), Matchers.containsInAnyOrder("ls", "cd", "cat", "touch", "rm", "cd .."));
        assertThat(builder.get("ls"), Matchers.instanceOf(ListAccount.class));
        assertThat(builder.get("cd"), Matchers.instanceOf(GoToTransactionFromAccount.class));
        assertThat(builder.get("rm"), Matchers.instanceOf(RemoveAccount.class));
        assertThat(builder.get("cat"), Matchers.instanceOf(CatTransaction.class));
        assertThat(builder.get("touch"), Matchers.instanceOf(TouchAccount.class));
        assertThat(builder.get("cd .."), Matchers.instanceOf(GoToBankFromAccount.class));
    }

    @Test
    public void testTransactionActor() throws NoSuchFieldException, IllegalAccessException {
        var bankActor = this.actors.get(Hierarchy.TRANSACTION);

        Field f = bankActor.getClass().getDeclaredField("builder");
        f.setAccessible(true);
        var builder = (HashMap<String, CommandBuilder>) f.get(bankActor);

        assertThat(builder.size(), Matchers.is(7));
        assertThat(builder.keySet(), Matchers.containsInAnyOrder("ls", "print", "touch", "rm", "cd ..", "group", "help"));
        assertThat(builder.get("cd .."), Matchers.instanceOf(GoToAccountFromTransaction.class));
        assertThat(builder.get("ls"), Matchers.instanceOf(ListTransaction.class));
        assertThat(builder.get("touch"), Matchers.instanceOf(TouchTransaction.class));
        assertThat(builder.get("group"), Matchers.instanceOf(GroupTransaction.class));
        assertThat(builder.get("help"), Matchers.instanceOf(HelpTransaction.class));
        assertThat(builder.get("rm"), Matchers.instanceOf(DeleteTransaction.class));
        assertThat(builder.get("print"), Matchers.instanceOf(PrintTransaction.class));

    }
}
