package main.examples.chapter_1.chapter_1_5.almaz;

/**
 * Created by Almaz on 20.06.2015.
 */
public abstract class DynamicConnectivity {
    public abstract void union(String[] args);
    public abstract void union(String arg);
    public abstract void union(String arg1, String arg2);
    public abstract boolean isConnected(String a, String b);
    public abstract int count();
    public void printHelp(){
        System.out.println("========== HELP ===========");
        System.out.println("union param1 ... param_N - for union objects");
        System.out.println("connected param1 param2 - answered is connected param1 and param2");
        System.out.println("count - to display count of element");
        System.out.println("exit - to exit from program");
    }
}
