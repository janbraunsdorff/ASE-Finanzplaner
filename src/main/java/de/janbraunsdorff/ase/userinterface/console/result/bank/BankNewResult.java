package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.Color;
import de.janbraunsdorff.ase.tech.printer.SentencePiece;
import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.ArrayList;
import java.util.List;

public class BankNewResult implements Result {

    private final BankEntity bankEntity;

    public BankNewResult(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    @Override
    public List<SentencePiece> print() {
        return new ArrayList<SentencePiece>() {{
            add(new SentencePiece(Color.WHITE, "Eine neune Bank wurde angelget\n"));
            add(new SentencePiece(Color.CYAN, String.format("ID: %s | Name: %s | Abkürzung: %s | Accounts: %d\n", bankEntity.getId(), bankEntity.getName(), bankEntity.getAcronym(), bankEntity.getAccounts().size())));
        }};
    }
}
