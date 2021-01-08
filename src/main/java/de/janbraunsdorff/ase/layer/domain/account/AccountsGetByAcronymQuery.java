package de.janbraunsdorff.ase.layer.domain.account;

import java.util.List;

public record AccountsGetByAcronymQuery(List<String> acronym) {

}
