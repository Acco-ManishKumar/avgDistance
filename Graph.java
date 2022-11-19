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

    void print() {
        for (int i = 0; i < this.vertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.graph.get(i).size(); j++) {
                int a = this.graph.get(i).get(j).Key();
                int b = this.graph.get(i).get(j).Value();
                System.out.print("{" + a + " " + b + "} ");
            }
            System.out.println();
        }
    }
}

public class Graph {
    public static void main(String[] args) {
        Integer vertices = 5;
        createGraph givenGraph = new createGraph(vertices);
        givenGraph.createEdges('A', 'B', 12);
        givenGraph.createEdges('A', 'C', 13);
        givenGraph.createEdges('A', 'E', 8);
        givenGraph.createEdges('A', 'D', 11);
        givenGraph.createEdges('D', 'E', 7);
        givenGraph.createEdges('E', 'C', 4);
        givenGraph.createEdges('B', 'C', 3);
        calculateAverageDistanceBetweenTwoPoints("A",  "C", givenGraph);
    }

    public static void calculateAverageDistanceBetweenTwoPoints(String X, String Y, createGraph givenGraph){
        Integer src = X.charAt(0)- 'A';
        Integer des = Y.charAt(0) - 'A';
        ArrayList<ArrayList<pair>> totalPath = new ArrayList<ArrayList<pair>>();
        ArrayList<pair> path = new ArrayList<pair>();
        dfs(givenGraph, totalPath, path, src, des, 0 );
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
    private static void dfs(createGraph givenGraph, ArrayList<ArrayList<pair>> totalPath, ArrayList<pair> path, Integer src, Integer des, Integer wgh) {
        path.add(new pair(src, wgh));
        if(src.equals(des)){
            totalPath.add(new ArrayList<pair>(path));
        }
        for(pair p : givenGraph.graph.get(src)){
            int a = p.Key;
            int b = p.Value;
            dfs(givenGraph, totalPath, path, a, des, b);
        }

        path.remove(path.size() - 1);

    }
}
