package de.janbraunsdorff.ase.layer.domain.account;

public record AccountCreateCommand(String bank, String name, String number,
                                   String acronym) {

}
