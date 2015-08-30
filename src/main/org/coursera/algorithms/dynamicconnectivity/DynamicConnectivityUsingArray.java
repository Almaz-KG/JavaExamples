package org.coursera.algorithms.dynamicconnectivity;

/**
 * Created by Almaz on 20.06.2015.
 */
public class DynamicConnectivityUsingArray extends DynamicConnectivity{
    protected int[] array;

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
    public void union(String arg) {
    }
    public void union(String arg1, String arg2) {
        try{
            int a = Integer.parseInt(arg1);
            int b = Integer.parseInt(arg2);
            union(a, b);
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
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


}
