package de.janbraunsdorff.ase.layer.persistence.database;

import de.janbraunsdorff.ase.layer.persistence.database.entity.ContractDatabaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContractSpringRepository extends CrudRepository<ContractDatabaseEntity, String> {

}
