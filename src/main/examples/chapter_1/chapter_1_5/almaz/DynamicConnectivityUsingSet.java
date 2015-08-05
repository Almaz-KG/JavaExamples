package examples.chapter_1.chapter_1_5.almaz;

import examples.chapter_1.chapter_1_5.almaz.DynamicConnectivity;

import java.util.*;

/**
 * Created by Almaz on 19.06.2015.
 */
public class DynamicConnectivityUsingSet extends DynamicConnectivity {
    private List<Set<String>> links;

    public DynamicConnectivityUsingSet() {
        this.links = new ArrayList<>();
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
                if(firstSet.size() > secondSet.size())
                    firstSet.addAll(secondSet);
                else
                    secondSet.addAll(firstSet);
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

}
