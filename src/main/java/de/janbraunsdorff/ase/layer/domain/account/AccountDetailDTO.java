package de.janbraunsdorff.ase.layer.domain.account;

import java.util.List;

public record AccountDetailDTO(
        String name,
        String acronym,
        String value,
        String last7,
        String last30,
        String max,
        String lastPosting,
        List<Integer> course
) {
}
