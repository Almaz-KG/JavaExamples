package main.examples.chapter_1.chapter_1_5.almaz;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Almaz on 20.06.2015.
 */
public class DynamicConnectivityUsingArray {
    private int[] array;

    public DynamicConnectivityUsingArray(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("Size cannot be a negative number");
        this.array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    public void union(String[] params){
        try{
            for (int i = 1; i < params.length; i++) {
                int a = Integer.parseInt(params[i - 1]);
                int b = Integer.parseInt(params[i]);
                union(a, b);
            }
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void union(int a, int b){
        if(a > array.length - 1)
            throw new IllegalArgumentException("a ");
        if(b > array.length - 1)
            throw new IllegalArgumentException("b ");

        int a_value = array[a];
        array[a] = array[b];
        for (int i = 0; i < array.length; i++) {
            if(array[i] == a_value)
                array[i] = array[b];
        }
    }
    public boolean isConnected(int a, int b){
        if(a > array.length - 1)
            return false;
        if(b > array.length - 1)
            return false;

        return array[a] == array[b];
    }
    public boolean isConnected(String a, String b){
        try{
            int a_value = Integer.parseInt(a);
            int b_value = Integer.parseInt(b);

            return isConnected(a_value, b_value);
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public int count(){
        return this.array.length;
    }
    public void printHelp(){
        System.out.println("========== HELP ===========");
        System.out.println("union param1 ... param_N - for union objects");
        System.out.println("connected param1 param2 - answered is connected param1 and param2");
        System.out.println("count - to display count of element");
        System.out.println("exit - to exit from program");
    }

    public static void main(String[] args) {
        int size = 100;
        DynamicConnectivityUsingArray dc = new DynamicConnectivityUsingArray(size);
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
