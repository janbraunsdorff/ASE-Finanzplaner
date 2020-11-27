package de.janbraunsdorff.ase.layer.domain.analyse;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class TransactionAnalyse {
    private final TransactionRepository repo;

    public TransactionAnalyse(TransactionRepository repo) {
        this.repo = repo;
    }

    public List<TransactionGroupDTO> groupMonthly(TransactionGroupCommand command){
        List<Transaction> transactionOfAccount = this.repo.getTransactionOfAccount(command.getAccount(), -1);

        List<TransactionGroupDTO> dtos = new ArrayList<>();

        TransactionGroupDTO dto = null;

        for (Transaction t : transactionOfAccount){
            if(dto == null || (t.getDate().getMonthValue() != dto.getMonth() || t.getDate().getYear() != dto.getYear())){
                if (dto != null){
                    dtos.add(dto);
                }
                dto = new TransactionGroupDTO(command.getAccount(), t.getDate().getMonthValue(), t.getDate().getYear());
            }

            if (t.getValue() > 0){
                dto.increment(t.getValue());
                if (t.getContract()){
                    dto.addIncomeContract(t.getValue());
                }
            }else {
                dto.decrement(t.getValue());
                if (t.getContract()){
                    dto.addOutcomeContract(t.getValue());
                }
            }
        }

        if (dto != null){
            dtos.add(dto);
        }

        int totalValue = 0;
        for(int i = dtos.size()-1; i >=0; i--){
            totalValue += dtos.get(i).getTotal();
            dtos.get(i).setValue(totalValue);
        }


        return dtos;
    }
}
