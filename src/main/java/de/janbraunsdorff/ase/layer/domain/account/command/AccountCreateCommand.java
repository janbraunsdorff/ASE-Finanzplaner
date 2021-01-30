package de.janbraunsdorff.ase.layer.domain.account.command;

public record AccountCreateCommand(String bank, String name, String number,
                                   String acronym) {

}
