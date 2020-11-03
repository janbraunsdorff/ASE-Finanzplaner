package de.janbraunsdorff.ase.layer.presentation.console.actions;


import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Action {
    Result act(String command);

    default Map<String, String> parseCommand(String command, int start){
        Map<String, String> parameter = new HashMap<>();
        String[] s = command.split(" ");
        Pattern pattern = Pattern.compile("^(-[a-z]+)$");

        StringJoiner builder =  new StringJoiner(" ");
        String tag = "";

        for (; start < s.length; start++ ){
            Matcher m = pattern.matcher(s[start]);

            if (m.find()){
                if (!tag.isEmpty()){
                    parameter.put(tag, builder.toString());
                }

                builder = new StringJoiner(" ");
                tag = m.group(0);
            }else {
                builder.add(s[start]);
            }
        }

        if (!tag.isEmpty()) {
            parameter.put(tag, builder.toString());
        }

        return parameter;
    }

    default boolean areTagsPresent( Map<String, String> map, String... tags){
        for (String s : tags){
            if (!map.containsKey(s)){
                return false;
            }
        }

        return true;
    }
}
