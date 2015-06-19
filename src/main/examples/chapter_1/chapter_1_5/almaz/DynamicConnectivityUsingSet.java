package main.examples.chapter_1.chapter_1_5.almaz;

import java.util.*;

/**
 * Created by Almaz on 19.06.2015.
 */
public class DynamicConnectivityUsingSet {
    private List<Set<String>> links;

    public DynamicConnectivityUsingSet() {
        this.links = new ArrayList<>();
    }

    public static void main(String[] args) {
        DynamicConnectivityUsingSet dc = new DynamicConnectivityUsingSet();
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

    public void union(String[] strings){
        if(strings.length == 1)
            union(strings[0]);
        else {
            for (int i = 1; i < strings.length; i++) {
                union(strings[i - 1], strings[i]);
            }
        }
    }
    public void union(String first){
        boolean isSingle = true;
        for (Set<String> link : links) {
            if(link.contains(first)) {
                isSingle = false;
                break;
            }
        }
        if(isSingle == true) {
            Set<String> set = new HashSet<>();
            set.add(first);
            links.add(set);
        }
    }
    public void union(String first, String second) {
        if(first == null)
            throw new IllegalArgumentException("First parameter is null");
        if(second == null)
            throw new IllegalArgumentException("Second parameter is null");


        Set<String> firstSet = null;
        Set<String> secondSet = null;
        for (Set<String> link : links) {
            if (link.contains(first))
                firstSet = link;
            if (link.contains(second))
                secondSet = link;

            if (firstSet != null && secondSet != null)
                break;
        }

        if(firstSet == secondSet && firstSet != null)
            return;
        if(firstSet == null) {
            if(secondSet == null) {
                Set<String> set = new HashSet<>();
                set.add(first);
                set.add(second);

                links.add(set);
            } else {
                secondSet.add(first);
            }
        } else {
            if(secondSet == null) {
                firstSet.add(second);
            } else {
                firstSet.addAll(secondSet);
                links.remove(secondSet);
            }
        }
    }
    public boolean isConnected(String first, String second) {
        for (Set<String> link : links) {
            if(link.contains(first) && link.contains(second))
                return true;
        }
        return false;
    }
    public int count(){
        int sum = 0;
        for (Set<String> link : links) {
            sum += link.size();
        }

        return sum;
    }
    public void printHelp(){
        System.out.println("========== HELP ===========");
        System.out.println("union param1 ... param_N - for union objects");
        System.out.println("connected param1 param2 - answered is connected param1 and param2");
        System.out.println("count - to display count of element");
        System.out.println("exit - to exit from program");
    }
}
