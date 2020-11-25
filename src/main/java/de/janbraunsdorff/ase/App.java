package de.janbraunsdorff.ase;


import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws AcronymAlreadyExistsException, AccountNotFoundException, IOException {
        new ApplicationConsoleBuilder()
                .intJsonRepo("./")
                //.intMemoryRepo()
                /*
                .addEntity(new Bank("Volksbank Karlsruhe eG", "VB"))
                .addEntity(new Account("VB", "Girokonto", "DE00 0000 0000 0000 0000 00", "VB-GK"))
                .addEntity(new Transaction("VB-GK", 10000, LocalDate.now().minusDays(2), "Jan Braunsdorff", "Start", false))
                .addEntity(new Transaction("VB-GK", -5000, LocalDate.now(), "Aldi", "Einkaufen", false))
                .addEntity(new Account("VB", "Geschäftsanteile", "DE00 0000 0000 0000 0000 01", "VB-GA"))
                .addEntity(new Account("VB", "Kreditkarte", "DE00 0000 0000 0000 0000 02", "VB-KK"))
                .addEntity(new Account("VB", "Depot", "DE00 0000 0000 0000 0000 03", "VB-DT"))
                .addEntity(new Bank("Spaßkasse", "SK"))
                .addEntity(new Account("SK", "Aktien", "DE00 0000 0000 0000 0000 04", "SK-AK"))
                .addEntity(new Account("SK", "Girokonto", "DE00 0000 0000 0000 0000 05", "SK-GK"))
                .addEntity(new Account("SK", "Vermögenswirksammeleistungen", "DE00 0000 0000 0000 0000 06", "SK-VL"))
                .addEntity(new Account("SK", "Altersvorsorge", "DE00 0000 0000 0000 0000 07", "SK-AV"))
                */
                .initDefaultApp()
                .createBankDistributor()
                .createAccountDistributor()
                .createTransactionDistributor()
                .createActionDistributor()
                // .createCli()
                .createOverlay()
                .run();

    }

}
