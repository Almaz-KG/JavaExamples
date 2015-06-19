package main.examples.chapter_1.chapter_1_5;

/**
 * Created by Almaz on 19.06.2015.
 */
public class DynamicConnectivity {
    private int[] id;
    private int count;


    public DynamicConnectivity(int size) {
        this.count= size;
        this.id = new int[size];

        for (int i = 0; i < this.id.length; i++) {
            this.id[i] = i;
        }
    }

    public void union(int p, int q) {
        throw new UnsupportedOperationException();
    }
    public int find(int q){
        throw new UnsupportedOperationException();
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    public int count(){
        return this.count;
    }

}
