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
        /* Generate list of rooms containing people and a map to their counts. */
        Map<Integer, Integer> room2Count = new HashMap<>();
        for(Map.Entry<Integer, Integer> count : counts.entrySet()) {
            if(count.getValue() > 0) {
                room2Count.put(count.getKey(), count.getValue());
            }
        }

        /* Maintain the planned capacity for each room. */
        Map<Integer, Integer> capacities = new HashMap<>(Constants.ROOMS_MAX_OCCUPANCY);

        /* Calculate the shortest paths for each room. */
        Double[] dist;

        /* Calculate paths until no nodes are left. */
        Vector<Vector<Integer>> paths = new Vector<>(room2Count.size());
        Vector<Integer> path;
        Integer room, bottleneckCapacity, currentCapacity, roomCount, node;
        while(!room2Count.isEmpty()){
            room = GetMinKey(room2Count);
            dist = Dijkstras(fires, capacities);
            path = GetPathToExit(dist, room);
            if(path.size() < 2)
                room2Count.remove(room);
            else {
                bottleneckCapacity = GetBottleneck(path, capacities);
                roomCount = room2Count.get(room);
                if(bottleneckCapacity > roomCount)
                    bottleneckCapacity = roomCount;
                /* Update path capacities */
                for (int i = 1; i < path.size() - 1; i++) {
                    node = path.get(i);
                    currentCapacity = capacities.get(node);
                    capacities.remove(node);
                    capacities.put(node, currentCapacity - bottleneckCapacity);
                }
                /* Update room2Count */
                room2Count.remove(room);
                room2Count.put(room, roomCount - bottleneckCapacity);
                if(room2Count.get(room) == 0)
                    room2Count.remove(room);
            }

            paths.add(path);
        }

        /* Map the edges in the graph to a physical map identified by indices in ROOMS_EDGE_TO_INDEX. */
        Map<Integer, Boolean> edges = new HashMap<>();
        for(int i = 0; i < Constants.ROOMS_EDGE_TO_INDEX.size(); i++) {edges.put(i, false);}
        String edge;
        for(Vector<Integer> path_ : paths) {
            for(int i = 0; i < path_.size() - 1; i++) {
                edge = "" + path_.get(i) + " " + path_.get(i+1);
                edges.put(Constants.ROOMS_EDGE_TO_INDEX.get(edge), true);
            }
        }

        return edges;
    }

    private Integer GetBottleneck(Vector<Integer> path, Map<Integer, Integer> capacities) {
        Integer minimum = Integer.MAX_VALUE;
        for(int i = 1; i < path.size() - 1; i++){
            if(capacities.get(path.get(i)) < minimum){
                minimum = capacities.get(path.get(i));
            }
        }
        return minimum;
    }

    private Integer GetMinKey(Map<Integer, Integer> room2Count) {
        Integer room = null;
        Integer min_value = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> roomCount: room2Count.entrySet()){
            if(roomCount.getValue() < min_value){
                min_value = roomCount.getValue();
                room = roomCount.getKey();
            }
        }
        return room;
    }

    private Double[] Dijkstras(Map<Integer, Boolean> fires, Map<Integer, Integer> capacities) {
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
                if(nextNode != Constants.ROOM_COUNT && capacities.get(nextNode) == 0){
                    continue;
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
