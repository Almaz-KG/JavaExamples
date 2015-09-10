package org.coursera.algorithms.dynamicconnectivity;

/**
 * Created by Almaz on 20.06.2015.
 */
public class DynamicConnectivityWeightedQuickUnion extends DynamicConnectivityQuickUnion{
    private int[] weights;

    @Override
    protected int root(int i) {
        while(i != array[i]) {
            array[i] = array[array[i]];
            i = array[i];
        }
        return i;
    }

    public DynamicConnectivityWeightedQuickUnion(int size) {
        super(size);
        this.weights = new int[size];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 1;
        }
    }
    @Override
    public void union(int a, int b) {
        int i = root(a);
        int j = root(b);

        if(i == j)
            return;
        if(weights[i] < weights[j]){
            array[i] = j;
            weights[j] += weights[i];
        } else {
            array[j] = i;
            weights[i] += weights[j];
        }
    }

    @Override
    public boolean isConnected(int a, int b) {
        return root(a) == root(b);
    }
}
