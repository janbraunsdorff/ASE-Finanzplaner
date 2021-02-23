package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionAddActionTest {

    private TransactionApplicationTestImplementation app;
    private TransactionAddAction action;

    @BeforeEach
    public void init(){
        this.app = new TransactionApplicationTestImplementation();
        this.action = new TransactionAddAction(app);
    }

    @Test
    public void addTransactionContract() throws Exception {
        var command = new ExpertCommand("transaction add -a acronym -val 123,56 -dat 01.01.2020 -thp third party -cat category -con", 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(TransactionAddResult.class));

        // check parameter
        assertThat(app.transactionCreateCommand.accountAcronym(), Matchers.is("acronym"));
        assertThat(app.transactionCreateCommand.category(), Matchers.is("category"));
        assertThat(app.transactionCreateCommand.value(), Matchers.is(12356));
        assertThat(app.transactionCreateCommand.date(), Matchers.is(LocalDate.of(2020, 1, 1)));
        assertThat(app.transactionCreateCommand.thirdParty(), Matchers.is("third party"));
        assertThat(app.transactionCreateCommand.isContract(), Matchers.is(true));
    }

    @Test
    public void addTransactionNoneContract() throws Exception {
        var command = new ExpertCommand("transaction add -a acronym -val 123,56 -dat 01.01.2020 -thp third party -cat category", 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(TransactionAddResult.class));

        // check parameter
        assertThat(app.transactionCreateCommand.accountAcronym(), Matchers.is("acronym"));
        assertThat(app.transactionCreateCommand.category(), Matchers.is("category"));
        assertThat(app.transactionCreateCommand.value(), Matchers.is(12356));
        assertThat(app.transactionCreateCommand.date(), Matchers.is(LocalDate.of(2020, 1, 1)));
        assertThat(app.transactionCreateCommand.thirdParty(), Matchers.is("third party"));
        assertThat(app.transactionCreateCommand.isContract(), Matchers.is(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "transaction add -a acronym -val 123,56 -dat 01.01.2020 -thp third party",
            "transaction add -a acronym -val 123,56 -dat 01.01.2020  -cat category",
            "transaction add -a acronym -val 123,56 -thp third party -cat category",
            "transaction add -a acronym -dat 01.01.2020 -thp third party -cat category",
            "transaction add -val 123,56 -dat 01.01.2020 -thp third party -cat category",
    })
    public void addTransactionMissingArgument(String commandInput) throws Exception {
        var command = new ExpertCommand(commandInput, 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(TransactionHelpResult.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "transaction add -a acronym -val 123,56 -dat 00.01.2020 -thp third party -cat category",
            "transaction add -a acronym -val 123,56 -dat -01.01.2020 -thp third party -cat category",
            "transaction add -a acronym -val 123,56 -dat 01,01,2020 -thp third party -cat category",
            "transaction add -a acronym -val 123,56 -dat 01012020 -thp third party -cat category",
            "transaction add -a acronym -val 123,56 -dat 01 01 2020 -thp third party -cat category",
            "transaction add -a acronym -val 123,56 -dat 01 2020 -thp third party -cat category",
    })
    public void addTransactionNotMaleFormedDate(String commandInput) throws Exception {
        var command = new ExpertCommand(commandInput, 2);

        assertThrows(DateTimeParseException.class,  () -> action.act(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "transaction add -a acronym -val 123,56€ -dat 01.01.2020 -thp third party -cat category",
    })
    public void addTransactionNotMaleFormedValue(String commandInput) throws Exception {
        var command = new ExpertCommand(commandInput, 2);

        assertThrows(NumberFormatException.class,  () -> action.act(command));
    }


}
