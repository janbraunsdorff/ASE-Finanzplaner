package de.janbraunsdorff.ase;


import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws AcronymAlreadyExistsException, AccountNotFoundException, IOException {
        // TODO: Command als Value Object
        new ApplicationConsoleBuilder()
                .intJsonRepo("./") //.intMemoryRepo()
                .initDefaultApp()
                .createBankDistributor()
                .createAccountDistributor()
                .createTransactionDistributor()
                .createActionDistributor()
                .createOverlay()// .createCli()
                .run();
    }

}
