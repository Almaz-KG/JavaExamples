package examples.chapter_1.chapter_1_5.almaz;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Almaz on 20.06.2015.
 */
public class Main {
    public static void main(String[] args) {
        int size = 100;
        DynamicConnectivity dc = new DynamicConnectivityQuickUnion(size);
        boolean running = true;

        Scanner sc = new Scanner(System.in);

        System.out.println("Dynamic connectivity\nPlease enter the command (help)");
        do{
            System.out.print("->");
            String text = sc.nextLine();
            String[] command = text.split(" ");

            switch (command[0].toLowerCase()){
                case "help":
                    dc.printHelp();
                    break;
                case "connected" :
                    System.out.println(dc.isConnected(command[1], command[2]));
                    break;
                case "union":
                    dc.union(Arrays.copyOfRange(command, 1, command.length));
                    break;
                case "count":
                    System.out.println("Count of elements: " + dc.count());
                    break;
                case "exit":
                    running = false;
                    System.out.println("Exit command entered");
                    break;
                default:
                    System.out.println("Unknown command, enter \"help\" for display commands");
            }

        } while (running);
    }
}
