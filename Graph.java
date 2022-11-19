import java.util.ArrayList;
import java.util.Scanner;

class pair{
    Integer Key, Value;
    public pair(Integer destination, Integer weight) {
        this.Key = destination;
        this.Value = weight;
    }
    Integer Key(){
        return this.Key;
    }
    Integer Value(){
        return this.Value;
    }
}
class createGraph {
    ArrayList<ArrayList<pair>> graph = new ArrayList<>();
    int vertices;
    createGraph(Integer vertices) {
        for (int i = 0; i <= 5; i++) {
            this.graph.add(new ArrayList<pair>());
            this.vertices = vertices;
        }
    }
    void createEdges(Character source, Character destination, int weight) {
        Integer intSource = source - 'A';
        Integer intDestination = destination - 'A';
        this.graph.get(intSource).add(new pair(intDestination, weight));
        ///remove next three for directed graph
        Integer intUndirectedSource = destination - 'A';
        Integer intUndirectedDestination = source - 'A';
        this.graph.get(intUndirectedSource).add(new pair(intUndirectedDestination, weight));
    }
}

public class Graph {
    public static void main(String[] args) {
        System.out.print("First source node : ");

        Scanner input1 = new Scanner(System.in);
        String x = input1.next().toUpperCase();
        System.out.print("Enter destination node : ");
        Scanner input2 = new Scanner(System.in);
        String y = input2.next().toUpperCase();

        calculateAverageDistanceBetweenTwoPoints(x,y);
    }
    public static void calculateAverageDistanceBetweenTwoPoints(String X, String Y){
        Integer vertices = 5;
        createGraph givenGraph = new createGraph(vertices);
        givenGraph.createEdges('A', 'B', 12);
        givenGraph.createEdges('A', 'C', 13);
        givenGraph.createEdges('A', 'E', 8);
        givenGraph.createEdges('A', 'D', 11);
        givenGraph.createEdges('D', 'E', 7);
        givenGraph.createEdges('E', 'C', 4);
        givenGraph.createEdges('B', 'C', 3);
        Integer source = X.charAt(0)- 'A';
        Integer destination = Y.charAt(0) - 'A';
        ArrayList<ArrayList<pair>> totalPath = new ArrayList<ArrayList<pair>>();
        ArrayList<pair> path = new ArrayList<pair>();
        ArrayList<Integer> visted = new ArrayList<>();
        for(int i = 0; i < givenGraph.vertices; i++){
            visted.add(0);
        }
        dfs(givenGraph, visted, totalPath, path, source, destination, 0 );
        int pathCount = totalPath.size();
        int distance = 0;
        for(ArrayList<pair> Path: totalPath){
            for(pair Edge : Path) {
                distance += Edge.Value();
            }
        }
        double averageDistance = (double)distance /pathCount;
        System.out.println("Average disstance between "+X+" and "+Y+" is :"+averageDistance);
    }
    private static void dfs(createGraph givenGraph, ArrayList<Integer> visted, ArrayList<ArrayList<pair>> totalPath, ArrayList<pair> path, Integer source, Integer destination, Integer weight) {
        visted.set(source, 1);
        path.add(new pair(source, weight));
        if(source.equals(destination)){
//            for (pair p : path){
//                System.out.print(p.Key + " ");
//            }
//            System.out.println();
            totalPath.add(new ArrayList<pair>(path));
        }
        for(pair p : givenGraph.graph.get(source)){
            if(visted.get(p.Key).equals(0)){
                int newSource = p.Key;
                int newWeight = p.Value;
                dfs(givenGraph, visted, totalPath, path, newSource, destination, newWeight);
            }

        }
        visted.set(source, 0);
        path.remove(path.size() - 1);
    }
}
