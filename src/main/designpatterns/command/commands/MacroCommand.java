package designpatterns.command.commands;

import java.util.List;

/**
 * Created by Almaz on 07.09.2015.
 */
public class MacroCommand implements Command {
    private List<Command> innerCommands;

    public MacroCommand(List<Command> innerCommands) {
        this.innerCommands = innerCommands;
    }

    @Override
    public void execute() {
        for (Command innerCommand : innerCommands) {
            innerCommand.execute();
        }
    }

    @Override
    public void undo() {
        for (Command innerCommand : innerCommands) {
            innerCommand.undo();
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (Command innerCommand : innerCommands) {
            sb.append(innerCommand + ", ");
        }
        return sb.toString();
    }
}
