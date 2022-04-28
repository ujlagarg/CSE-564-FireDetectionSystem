package primaryController;

import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Router {
    private final Map<Integer, Map<Integer, Double>> graph;
    public Map<Integer, Integer[]> roomMap = new HashMap<Integer, Integer[]>();

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
            Map<String, Map<String, Double>> temp = new ObjectMapper().readValue(fileContent, HashMap.class);
            this.graph = new HashMap<>();
            for(Map.Entry<String, Map<String, Double>> U: temp.entrySet()) {
                this.graph.put(Integer.parseInt(U.getKey()), new HashMap<>());
                ArrayList<Integer> rooms = new ArrayList<Integer>();
                for(Map.Entry<String, Double> V : temp.get(U.getKey()).entrySet()) {
                    this.graph.get(Integer.parseInt(U.getKey())).put(Integer.parseInt(V.getKey()), V.getValue());
                    rooms.add(Integer.parseInt(V.getKey()));

                }
                Integer[] intRooms = new Integer[rooms.size()];
                intRooms = rooms.toArray(intRooms);
                roomMap.put(Integer.parseInt(U.getKey()), intRooms);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<Integer, Boolean> update(Map<Integer, Integer> counts, Map<Integer, Boolean> fires) {
        /* Construct a new graph with all nodes containing fires removed. */
        Map<Integer, Map<Integer, Double>> graph = new HashMap<>(this.graph);
        for(Map.Entry<Integer, Boolean> room : fires.entrySet()) {
            if(room.getValue()){
                graph.remove(room.getKey());

                /* Remove all paths to the room */
                for(Map.Entry<Integer, Map<Integer, Double>> targets : graph.entrySet()) {
                    targets.getValue().remove(room.getKey());
                }
            }
        }

        /* Generate list of rooms containing people. */
        Vector<Integer> containsPeople = new Vector<>();
        for(Map.Entry<Integer, Integer> count : counts.entrySet()) {
            if(count.getValue() > 0) {
                containsPeople.add(count.getKey());
            }
        }

        /* Calculate the shortest paths for each room */
        Double[] dist = Dijkstras(fires);
        Vector<Vector<Integer>> paths = new Vector<>(containsPeople.size());
        for(Integer room: containsPeople){
            paths.add(GetPathToExit(dist, room));
        }

        Map<Integer, Boolean> edges = new HashMap<>();
        for(int i = 0; i < Constants.ROOMS_EDGE_TO_INDEX.size(); i++) {
            edges.put(i, false);
        }

        String edge;
        for(Vector<Integer> path : paths) {
            for(int i = 0; i < path.size() - 1; i++) {
                edge = "" + path.get(i) + " " + path.get(i+1);
                edges.put(Constants.ROOMS_EDGE_TO_INDEX.get(edge), true);
            }
        }

        return edges;
    }

    private Double[] Dijkstras(Map<Integer, Boolean> fires) {
        /* Set all values to infinity */
        Double[] dist = new Double[this.graph.size()];
        for(int j = 0; j < this.graph.size(); j++) {
            dist[j] = Double.POSITIVE_INFINITY;
        }
        dist[Constants.ROOM_COUNT] = 0.0;

        /* Initialize variables */
        Vector<Integer> toExplore = new Vector<>();
        toExplore.add(Constants.ROOM_COUNT);
        Set<Integer> visited = new HashSet<>();

        int current, currentIndex, nextNode;
        double next_dist;
        while(toExplore.size() > 0){
            currentIndex = GetNextNode(toExplore, dist);
            current = toExplore.get(currentIndex);
            toExplore.remove(currentIndex);
            visited.add(current);

            /* Add each of the connected nodes to toExplore */
            for(Map.Entry<Integer, Double> node : this.graph.get(current).entrySet()) {
                next_dist = dist[current] + node.getValue();
                nextNode = node.getKey();
                if(fires.containsKey(node.getKey())){
                    if(fires.get(node.getKey())){
                        continue;
                    }
                }
                if(next_dist < dist[nextNode])
                    dist[nextNode] = next_dist;
                if(!visited.contains(nextNode))
                    toExplore.add(nextNode);
            }
        }

        return dist;
    }

    private int GetNextNode(Vector<Integer> toExplore, Double[] dist) {
        int minNode = -1, node;
        Double minDistance = Double.POSITIVE_INFINITY;
        for(int i = 0; i < toExplore.size(); i++){
            node = toExplore.get(i);
            if(dist[node] < minDistance) {
                minDistance = dist[node];
                minNode = i;
            }
        }
        return minNode;
    }

    private Vector<Integer> GetPathToExit(Double[] dist, Integer room) {
        Vector<Integer> path = new Vector<>();
        int current = room, considered, minNode;
        Double minDist;
        path.add(room);
        System.out.println(this.graph);
        for(int i = 0; i < this.graph.size(); i++){
            minDist = Double.POSITIVE_INFINITY;
            minNode = -1;
            for(Map.Entry<Integer, Double> node : this.graph.get(current).entrySet()){
                considered = node.getKey();
                if(considered == Constants.ROOM_COUNT){
                    minNode = Constants.ROOM_COUNT;
                    break;
                }
                if(dist[considered] < minDist){
                    minNode = considered;
                    minDist = dist[considered];
                }
            }
            path.add(minNode);
            current = minNode;
            if(minNode == Constants.ROOM_COUNT)
                break;
            if(minNode == -1) {
                System.out.println("🔥 Room " + room + " has no viable paths");
                return new Vector<>();
            }
        }

        return path;
    }
}
