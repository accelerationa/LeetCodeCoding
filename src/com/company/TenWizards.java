package com.company;


import java.util.*;

public class TenWizards {
    public int findMinimumPath(List<Set<Integer>> adjacencyList, int from, int to) {
        Queue<Integer> queue = new LinkedList();
        Map<Integer, Integer> distanceMap = new HashMap<>();
        Map<Integer, Set<Integer>> edges = new HashMap();

        for(int i=0; i<10; i++) {
            edges.put(i, new HashSet<>());
        }

        if(from == to) return 0;
        queue.add(from);
        distanceMap.put(from, 0);

        while(!queue.isEmpty()) {
            int vertex = queue.poll();

            for(int neighbor: adjacencyList.get(vertex)) {
                queue.add(neighbor);

                if(distanceMap.containsKey(neighbor)) {
                    int diff = distanceMap.get(neighbor) - distanceMap.get(vertex) - calculateDistance(vertex, neighbor);
                    if(diff > 0) {
                        for(Set<Integer> children: edges.values()) {
                            if(children.contains(neighbor)) {
                                children.remove(neighbor);
                            }
                        }
                        edges.get(vertex).add(neighbor);
                        updateDistances(distanceMap, edges, neighbor, diff);

                    }
                } else {
                    distanceMap.put(neighbor, distanceMap.get(vertex) + calculateDistance(vertex, neighbor));
                    edges.get(vertex).add(neighbor);
                }
            }
        }
        if(distanceMap.get(to) == null) return -1;
        return distanceMap.get(to);
    }

    private void updateDistances (Map<Integer, Integer> distanceMap, Map<Integer, Set<Integer>> edges, int vertex, int diff) {
        for(int neighbor: edges.get(vertex)) {
            distanceMap.put(neighbor, distanceMap.get(neighbor) - diff);
        }
    }

    private int calculateDistance(int i, int j) {
        return (int)Math.pow((i-j), 2);
    }
}
