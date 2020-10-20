package de.janbraunsdorff.ase;

import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.memory.MemoryRepository;
import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.userinterface.console.UseCaseController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws IOException {
        CrudBankRepository repo = new MemoryRepository();
        CrudBank crudBank = new CrudBank(repo);
        UseCaseController controller = new UseCaseController(crudBank);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            String name = reader.readLine();
            controller.answer(name);
        }
    }
}
