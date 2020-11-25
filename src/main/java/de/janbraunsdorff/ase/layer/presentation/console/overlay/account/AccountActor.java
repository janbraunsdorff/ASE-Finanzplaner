package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

import java.util.HashMap;

public class AccountActor {
    private final HashMap<String, CommandBuilder> builder = new HashMap<String, CommandBuilder>(){{
        put("ls", new List());
        put("cd", new GoToTransaction());
        put("cd ..", new GoToBank());
    }};

    public State act(State state, String command){
        String cmd = command.split(" ")[0];
        return this.builder.get(cmd).build(state, command);
    }
}

