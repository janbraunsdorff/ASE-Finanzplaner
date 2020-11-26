package de.janbraunsdorff.ase.layer.presentation.console.overlay.bank;


import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

import java.util.HashMap;

public class BankActor {

    private final HashMap<String, CommandBuilder> builder = new HashMap<String, CommandBuilder>(){{
        put("ls", new List());
        put("cd", new GoToAccount());
        put("cat", new Cat());
        put("touch", new Touch());
        put("rm", new Remove());
    }};

    public State act(State state, Command command){
        String cmd = command.getTopLevel();
        return this.builder.getOrDefault(cmd, (state1, command1) -> state1.stay(new Command("bank", 0))).build(state, command);
    }
}
