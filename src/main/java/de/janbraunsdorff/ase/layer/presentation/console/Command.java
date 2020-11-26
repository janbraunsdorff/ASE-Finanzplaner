package de.janbraunsdorff.ase.layer.presentation.console;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Command {
    private final Map<String, String> parameter;
    private final String topLevel;
    private final String secondLevel;
    private final String command;

    public Command(String command, int start) {
        this.command = command;
        String[] s = command.split(" ");
        this.topLevel = s[0];
        this.secondLevel = s.length >= 2 ? s[1] : "";
        this.parameter = new HashMap<>();

        parseCommand(command, start);
    }

    public String getTopLevel() {
        return topLevel;
    }

    public String getSecondLevel() {
        return secondLevel;
    }


    public String getParameter(String parameter){
        return this.parameter.get(parameter);
    }

    private void parseCommand(String command, int start) {
        String[] s = command.split(" ");
        Pattern pattern = Pattern.compile("^(-[a-z]+)$");

        StringJoiner builder = new StringJoiner(" ");
        String tag = "";

        for (; start < s.length; start++) {
            Matcher m = pattern.matcher(s[start]);

            if (m.find()) {
                if (!tag.isEmpty()) {
                    this.parameter.put(tag, builder.toString());
                }

                builder = new StringJoiner(" ");
                tag = m.group(0);
            } else {
                builder.add(s[start]);
            }
        }

        if (!tag.isEmpty()) {
            this.parameter.put(tag, builder.toString());
        }

    }

    public boolean areTagsAndValuesPresent(String... tags) {
        for (String s : tags) {
            if (!parameter.containsKey(s)) {
                return false;
            }

            if (parameter.get(s).equals("")) {
                return false;
            }
        }

        return true;
    }

    public boolean areTagsPresent(String... tags) {
        for (String s : tags) {
            if (!parameter.containsKey(s)) {
                return false;
            }
        }

        return true;
    }

    public Command changeStart(int i) {
        return  new Command(this.command, i);
    }
}
