import java.util.ArrayList;
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
    }
}

public class Graph {
    public static void main(String[] args) {
        calculateAverageDistanceBetweenTwoPoints("A",  "C");
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
        dfs(givenGraph, totalPath, path, source, destination, 0 );
        int pathCount = totalPath.size();
        int distance = 0;
        for(ArrayList<pair> it: totalPath){
            for(pair it1 : it) {
                distance += it1.Value();
            }
        }
        double averageDistance = (double)distance /pathCount;
        System.out.println(averageDistance);
    }
    private static void dfs(createGraph givenGraph, ArrayList<ArrayList<pair>> totalPath, ArrayList<pair> path, Integer source, Integer destination, Integer weight) {
        path.add(new pair(source, weight));
        if(source.equals(destination)){
            totalPath.add(new ArrayList<pair>(path));
        }
        for(pair p : givenGraph.graph.get(source)){
            int newSource = p.Key;
            int newWeight = p.Value;
            dfs(givenGraph, totalPath, path, newSource, destination, newWeight);
        }
        path.remove(path.size() - 1);
    }
}
