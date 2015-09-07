package designpatterns.command;

import designpatterns.command.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Almaz on 07.09.2015.
 */
public class RemoteControl {
    private Map<String, Command> commands;

    public RemoteControl() {
        commands = new HashMap<>();
    }

    public void setCommandForButton(String button, Command cmd){
        commands.put(button, cmd);
    }

    public void pushButton(String command){
        if(commands.containsKey(command))
            commands.get(command).execute();
    }
    public void undoButton(String command){
        if(commands.containsKey(command))
            commands.get(command).undo();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Выберите вариант действия:\n");

        for (String s : commands.keySet()) {
            sb.append(s + " - " + commands.get(s) + "\n");
        }
        return sb.toString();
    }
}
