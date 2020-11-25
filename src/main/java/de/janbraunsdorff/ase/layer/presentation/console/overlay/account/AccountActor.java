package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

import java.util.HashMap;

public class AccountActor {
    private final HashMap<String, CommandBuilder> builder = new HashMap<String, CommandBuilder>(){{
        put("ls", new List());
        put("cd", new GoToTransaction());
        put("rm", new Remove());
        put("cat", new Cat());
        put("touch", new Touch());
        put("cd ..", new GoToBank());
    }};

    public State act(State state, String command){
        if (command.equals("cd ..")){
            return this.builder.get("cd ..").build(state, command);

        }
        String cmd = command.split(" ")[0];
        return this.builder.getOrDefault(cmd, (state1, command1) -> state1.stay("account")).build(state, command);
    }
}

