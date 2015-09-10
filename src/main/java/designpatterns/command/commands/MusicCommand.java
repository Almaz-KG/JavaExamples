package designpatterns.command.commands;

import designpatterns.command.systems.Music;

/**
 * Created by Almaz on 07.09.2015.
 */
public class MusicCommand implements Command {
    private Music music;

    public MusicCommand(Music music) {
        this.music = music;
    }

    @Override
    public void execute() {
        music.turnOn();
    }

    @Override
    public void undo() {
        music.turnOff();
    }

    @Override
    public String toString() {
        return "Включить музыку";
    }
}
