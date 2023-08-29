package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import static search.search.scc;
import static search.search.invIndex;
import static search.search.list;

interface matchingStrategy {
    void searchFolk();
}
class MatchChecker{
    private matchingStrategy msy;
    public void setStrategy(matchingStrategy msy){
        this.msy = msy;
    }
    public void searchFolk(){
        this.msy.searchFolk();
    }
}
class searchAll implements matchingStrategy{
    @Override
    public void searchFolk(){
        System.out.println("Enter a name or email to search all suitable people.");
        String word = scc.nextLine().toLowerCase();
        String[] words = word.split(" ");
        Set<String> finalArray = new TreeSet<>();
        boolean sl = false;
        Set<Integer> variance = new HashSet<>();

//            variance.addAll(invIndex.get(words[0]));
        for(int i = 0; i <words.length; i++){
            if (!(invIndex.get(words[i]) == null)){
                if (variance.isEmpty()){
                    for(int ff:invIndex.get(words[i])){
                        variance.add(ff);
                    }
                } else{
                    variance.retainAll(invIndex.get(words[i]));
                }
            }
        }

        for (int xy: variance){ //loop through array list
            finalArray.add(list.get(xy));
            sl = true;
        }

        if (!sl){
            System.out.println("No matching people found.");
        } else {
            int num = finalArray.size();
            System.out.println(num + " persons found:");
            for (String x:finalArray) {
                System.out.println(x);
            }
        }
    }
}
class searchAny implements matchingStrategy{
    @Override
    public void searchFolk(){
        System.out.println("Enter a name or email to search all suitable people.");
        String word = scc.nextLine().toLowerCase();
        String[] words = word.split(" ");
        Set<String> finalArray = new TreeSet<>();
        boolean sl = false;
        Set<ArrayList<Integer>> variance = new HashSet<>();

        for(String ww: words){
            variance.add(invIndex.get(ww));
        }

        for (ArrayList<Integer> xy: variance){ //loop through array list
            if (!(xy == null)){
                for (int i = 0; i < xy.size(); i++){  //loop through internal arrays
                    int num = xy.get(i); //select integer
                    for (ArrayList<Integer> xyz: variance){ // loop through array list
                        if (xyz.contains(num)){ //if array list contains number, {should be checking other array lists}
                            finalArray.add(list.get(num)); //add to list. Should be set*.
                            sl = true;
                        }
                    }
                }
            }
        }

        if (!sl){
            System.out.println("No matching people found.");
        } else {
            int num = finalArray.size();
            System.out.println(num + " persons found:");
            for (String x:finalArray) {
                System.out.println(x);
            }
        }
    }
}
class searchNone implements matchingStrategy{
    @Override
    public void searchFolk(){
        System.out.println("Enter a name or email to search all suitable people.");
        String word = scc.nextLine().toLowerCase();
        String[] words = word.split(" ");
        Set<String> finalArray = new TreeSet<>();
        for (String t: list){
            finalArray.add(t);
        }
        Set<String> removeArray = new HashSet<>();
        boolean sl = false;
        Set<ArrayList<Integer>> variance = new HashSet<>();

        for(String ww: words){
            variance.add(invIndex.get(ww));
        }

        for (ArrayList<Integer> xy: variance){ //loop through array list
            if (!(xy == null)){
                for (int i = 0; i < xy.size(); i++){  //loop through internal arrays
                    int num = xy.get(i); //select integer
                    for (ArrayList<Integer> xyz: variance){ // loop through array list
                        if (xyz.contains(num)){ //if array list contains number, {should be checking other array lists}
                            removeArray.add(list.get(num)); //add to list. Should be set*.
                            sl = true;
                        }
                    }
                }
            }
        }

        finalArray.removeAll(removeArray);

        if (!sl){
            System.out.println("No matching people found.");
        } else {
            int num = finalArray.size();
            System.out.println(num + " persons found:");
            for (String x:finalArray) {
                System.out.println(x);
            }
        }
    }
}