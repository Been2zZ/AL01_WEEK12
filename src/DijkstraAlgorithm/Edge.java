package DijkstraAlgorithm;

public class Edge implements Comparable<Edge>{
    int dest;
    int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge p) {
        if(this.weight < p.weight) return -1;
        else if(this.weight == p.weight) return 0;
        else return 1;
    }
}
