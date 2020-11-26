package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Actor;
import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

import java.util.HashMap;

public class AccountActor implements Actor {
    private final HashMap<String, CommandBuilder> builder = new HashMap<String, CommandBuilder>(){{
        put("ls", new List());
        put("cd", new GoToTransaction());
        put("rm", new Remove());
        put("cat", new Cat());
        put("touch", new Touch());
        put("cd ..", new GoToBank());
    }};

    public State act(State state, Command command){
        if (command.getTopLevel().equals("cd") && command.getSecondLevel().equals("..")){
            return this.builder.get("cd ..").build(state, command);
        }
        String cmd = command.getTopLevel();
        return this.builder.getOrDefault(cmd, (state1, command1) -> state1.stay(new Command("account", 0))).build(state, command);
    }
}

