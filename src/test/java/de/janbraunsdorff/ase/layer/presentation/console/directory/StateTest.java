package de.janbraunsdorff.ase.layer.presentation.console.directory;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class StateTest {

    @Test
    public void createInitState(){
        var state = State.createInitState();
        assertThat(state.hierarchy(), Matchers.is(Hierarchy.BANK));
        assertThat(state.bankIdent(), Matchers.nullValue());
        assertThat(state.accountIdent(), Matchers.nullValue());
    }

    @Test
    public void goUpBankToBank(){
        var state = new State(Hierarchy.BANK, "", "");
        state = state.move(new OverlayCommand(null, StateTransition.UP));
        assertThat(state.getHierarchy(), Matchers.is(Hierarchy.CONTRACT));
    }

    @Test
    public void goUpAccountToBank(){
        var state = new State(Hierarchy.ACCOUNT, "bank_init", "");
        state = state.move(new OverlayCommand(null, StateTransition.UP));

        assertThat(state.getHierarchy(), Matchers.is(Hierarchy.BANK));
        assertThat(state.bankIdent(), Matchers.nullValue());
    }

    @Test
    public void goUpTransactionToAccount(){
        var state = new State(Hierarchy.TRANSACTION, "bank_init", "account_init");
        state = state.move(new OverlayCommand(null, StateTransition.UP));
        assertThat(state.getHierarchy(), Matchers.is(Hierarchy.ACCOUNT));
        assertThat(state.accountIdent(), Matchers.nullValue());
        assertThat(state.bankIdent(), Matchers.is("bank_init"));
    }

    @Test
    public void goDeeperBankToAccount(){
        var state = new State(Hierarchy.BANK, "", "");
        state = state.move(new OverlayCommand(null, StateTransition.DEEPER, "bank"));

        assertThat(state.getHierarchy(), Matchers.is(Hierarchy.ACCOUNT));
        assertThat(state.bankIdent(), Matchers.is("bank"));
    }

    @Test
    public void goDeeperAccountToTransaction(){
        var state = new State(Hierarchy.ACCOUNT, "bank_init", "");
        state = state.move(new OverlayCommand(null, StateTransition.DEEPER, "account"));

        assertThat(state.getHierarchy(), Matchers.is(Hierarchy.TRANSACTION));
        assertThat(state.accountIdent(), Matchers.is("account"));
        assertThat(state.bankIdent(), Matchers.is("bank_init"));

    }

    @Test
    public void goDeeperTransactionToTransaction(){
        var state = new State(Hierarchy.TRANSACTION, "bank_init", "account_init");
        state = state.move(new OverlayCommand(null, StateTransition.DEEPER, "ident"));

        assertThat(state.getHierarchy(), Matchers.is(Hierarchy.TRANSACTION));
        assertThat(state.accountIdent(), Matchers.is("account_init"));
        assertThat(state.bankIdent(), Matchers.is("bank_init"));
    }

    @Test
    public void stayBank(){
        var state1 = new State(Hierarchy.BANK, "", "");
        var state2 = state1.move(new OverlayCommand(null, StateTransition.STAY));
        assertThat(state1, Matchers.is(state2));
    }

    @Test
    public void stayAccount(){
        var state1 = new State(Hierarchy.ACCOUNT, "bank", "");
        var state2 = state1.move(new OverlayCommand(null, StateTransition.STAY));
        assertThat(state1, Matchers.is(state2));
    }

    @Test
    public void stayTransaction(){
        var state1 = new State(Hierarchy.TRANSACTION, "bank", "account");
        var state2 = state1.move(new OverlayCommand(null, StateTransition.STAY));
        assertThat(state1, Matchers.is(state2));
    }

    @Test
    public void bankPath(){
        var state1 = new State(Hierarchy.BANK, null, null);
        assertThat(state1.getPath(), Matchers.is("[Bank]/"));
    }

    @Test
    public void accountPath(){
        var state1 = new State(Hierarchy.BANK, "account", null);
        assertThat(state1.getPath(), Matchers.is("[Bank]/account/"));
    }

    @Test
    public void transactionPath(){
        var state1 = new State(Hierarchy.BANK, "account", "transaction");
        assertThat(state1.getPath(), Matchers.is("[Bank]/account/transaction/"));
    }
}
