package de.janbraunsdorff.ase.layer.domain.transaction;


import java.time.LocalDate;

public record TransactionCreateCommand(String accountAcronym, Integer value,
                                       LocalDate date, String thirdParty, String category,
                                       Boolean isContract) {

}
