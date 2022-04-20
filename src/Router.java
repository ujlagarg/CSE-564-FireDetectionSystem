import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Router {
    private final Map<Integer, Map<Integer, Float>> graph;

    /**
     * Loads the map from a JSON file.
     * <br/>
     * This method is a constructor. A JSON file <code>./data/map_*.json</code> is loaded and is
     * converted to a graph, formatted as an adjacency list.
     */
    public Router() {
        /* Load graph */
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("data/map.json"));
            String fileContent = new String (bytes);
            this.graph = new ObjectMapper().readValue(fileContent, HashMap.class);
            System.out.println(this.graph.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Vector<Vector<Integer>> update(Map<Integer, Integer> counts, Map<Integer, Boolean> fires) {
        /* Construct a new graph with all nodes containing fires removed. */
        Map<Integer, Map<Integer, Float>> graph = new HashMap<Integer, Map<Integer, Float>>(this.graph);
        for(Map.Entry<Integer, Boolean> room : fires.entrySet()) {
            if((boolean) room.getValue()){
                graph.remove(room.getKey());

                /* Remove all paths to the room */
                for(Map.Entry<Integer, Map<Integer, Float>> targets : graph.entrySet()) {
                    targets.getValue().remove(room.getKey());
                }
            }
        }

        /* Generate list of rooms containing people. */
        Vector<Integer> containsPeople = new Vector<Integer>();
        for(Map.Entry<Integer, Integer> count : counts.entrySet()) {
            if(count.getValue() > 0) {
                containsPeople.add(count.getKey());
            }
        }

        /* Calculate the shortest paths for each room */
        Float[] dist = Dijkstras();
        Vector<Vector<Integer>> paths = new Vector<>(containsPeople.size());
        for(Integer room: containsPeople){
            paths.add(getPathToExit(dist, room));
        }

        return paths;
    }

    private Float[] Dijkstras() {
        /* Set all values to infinity */
        Float[] dist = new Float[this.graph.size()];
        for(int j = 0; j < this.graph.size(); j++) {
            dist[j] = Float.POSITIVE_INFINITY;
        }
        dist[0] = 0f;

        /* Initialize variables */
        Vector<Integer> toExplore = new Vector<>();
        toExplore.add(0);
        Set<Integer> visited = new HashSet<>();

        int current, nextNode;
        float next_dist;
        while(toExplore.size() > 0){
            current = GetNextNode(toExplore, dist);
            toExplore.remove(current);
            visited.add(current);
            /* Add each of the connected nodes to toExplore */
            for(Map.Entry<Integer, Float> node : this.graph.get(current).entrySet()) {
                next_dist = dist[current] + node.getValue();
                nextNode = node.getKey();
                if(next_dist < dist[nextNode])
                    dist[nextNode] = next_dist;
                if(!visited.contains(nextNode))
                    toExplore.add(nextNode);
            }
        }

        return dist;
    }

    private int GetNextNode(Vector<Integer> toExplore, Float[] dist) {
        int minNode = -1;
        Float minDistance = Float.POSITIVE_INFINITY;
        for(Integer node: toExplore){
            if(dist[node] < minDistance) {
                minDistance = dist[node];
                minNode = node;
            }
        }
        return minNode;
    }

    private Vector<Integer> getPathToExit(Float[] dist, Integer room) {
        Vector<Integer> path = new Vector<>();
        Boolean reached = false;
        int current = room, considered, minNode;
        Float minDist;
        path.add(room);
        for(int i = 0; i < this.graph.size(); i++){
            minDist = Float.POSITIVE_INFINITY;
            minNode = -1;
            for(Map.Entry<Integer, Float> node : this.graph.get(current).entrySet()){
                considered = node.getKey();
                if(considered == 0){
                    minNode = 0;
                    break;
                }
                if(dist[considered] < minDist){
                    minNode = considered;
                    minDist = dist[considered];
                }
            }
            path.add(minNode);
            current = minNode;
            if(minNode == 0)
                break;
        }

        return path;
    }

    public static void main() {
        Map<Integer, Integer> counts;
        Map<Integer, Boolean> detected;
    }
}
