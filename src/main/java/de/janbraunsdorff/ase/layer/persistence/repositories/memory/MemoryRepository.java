package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;

import java.util.HashMap;
import java.util.Map;

public class MemoryRepository {
    protected final Map<String, BankMemoryEntity> memory = new HashMap<>();
}
