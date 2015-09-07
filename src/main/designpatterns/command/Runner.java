package designpatterns.command;

import designpatterns.command.commands.*;
import designpatterns.command.systems.Light;
import designpatterns.command.systems.Music;
import designpatterns.command.systems.TV;
import designpatterns.command.systems.Teapot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Almaz on 07.09.2015.
 */
public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommandForButton("1", new LightsCommand(new Light()));
        remoteControl.setCommandForButton("2", new TVCommand(new TV()));
        remoteControl.setCommandForButton("3", new MusicCommand(new Music()));
        remoteControl.setCommandForButton("4", new TeapotCommand(new Teapot()));


        Command teaCommand = new TeapotCommand(new Teapot());
        Command tvCommand = new TVCommand(new TV());

        MacroCommand macroCommand = new MacroCommand(Arrays.asList(teaCommand, tvCommand));
        remoteControl.setCommandForButton("5", macroCommand);


        String command = null;
        do{
            System.out.println(remoteControl);
            System.out.print("Ваш выбор: ");
            command = scanner.nextLine();

            remoteControl.pushButton(command);

            remoteControl.undoButton(command);

            System.out.print("Продолжить выполнение (y|n):");
            command = scanner.nextLine();
        } while ("y".equals(command));
    }
}
