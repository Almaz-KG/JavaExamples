package main.examples.chapter_1.chapter_1_5.almaz;

/**
 * Created by Almaz on 20.06.2015.
 */
public class DynamicConnectivityQuickUnion extends DynamicConnectivityUsingArray {
    public DynamicConnectivityQuickUnion(int size) {
        super(size);
    }

    protected int root(int i){
        while(i != array[i])
            i = array[i];
        return i;
    }
    @Override
    public void union(int a, int b){
        if(a > array.length - 1)
            throw new IllegalArgumentException("a ");
        if(b > array.length - 1)
            throw new IllegalArgumentException("b ");

        int i = root(a);
        int j = root(b);
        array[i] = j;
    }

    public boolean isConnected(int a, int b){
        return root(a) == root(b);
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
