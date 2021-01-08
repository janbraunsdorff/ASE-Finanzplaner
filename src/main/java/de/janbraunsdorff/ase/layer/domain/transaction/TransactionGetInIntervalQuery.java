package de.janbraunsdorff.ase.layer.domain.transaction;

import java.time.LocalDate;
import java.util.List;

public final record TransactionGetInIntervalQuery(List<String> account, LocalDate start,
                                                  LocalDate end) {


}
