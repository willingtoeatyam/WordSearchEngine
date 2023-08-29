package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class search {
    static Scanner sc, scc;
    static MatchChecker mck = new MatchChecker();
    static Map<String, ArrayList<Integer>> invIndex = new HashMap<>();
    static String filename;
    static List<String> list = new ArrayList<String>();


    public static void searchWords(String[] args) throws FileNotFoundException {
        sc = new Scanner(System.in);
        scc = new Scanner(System.in);

        if (args[0].equals("--data")){filename = args[1];}
//        filename = "C:\\Users\\User\\IdeaProjects\\Simple Search Engine (Java)\\Simple Search Engine (Java)\\task\\src\\search\\text.txt";

        File fs =  new File(filename);
        int linecount = 0;
        Scanner fsc = new Scanner(fs);
        try {
            while (fsc.hasNextLine()){
                String person = fsc.nextLine();
                list.add(person);
                String[] lines = person.split(" ");
                for (String yy: lines){
                    if (invIndex.containsKey(yy.toLowerCase())){
                        invIndex.get(yy.toLowerCase()).add(linecount);
                    } else {
                        invIndex.put(yy.toLowerCase(), new ArrayList<>());
                        invIndex.get(yy.toLowerCase()).add(linecount);
                    }
                }
                linecount++;
            }
        }  finally {
            fsc.close();
        }
        printMenu();
    }

    public static void printMenu(){
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
        String opt = sc.next();
        switch (opt){
            case "1":
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                String strategy = sc.next();
                switch(strategy){
                    case "ALL":
                        mck.setStrategy(new searchAll());
                        mck.searchFolk();
                        //do all
                        break;
                    case "ANY":
                        mck.setStrategy(new searchAny());
                        mck.searchFolk();
                        //do any
                        break;
                    case "NONE":
                        mck.setStrategy(new searchNone());
                        mck.searchFolk();
                        //do none
                        break;
                    default:
                        break;
                }
                printMenu();
                break;
            case "2":
                System.out.println("=== List of people ===");
                for (String p: list){
                    System.out.println(p);
                }
                printMenu();
                break;
            case "0":
                System.out.println("Bye!");
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect option! Try again.");
                printMenu();
                break;
        }
    }
}