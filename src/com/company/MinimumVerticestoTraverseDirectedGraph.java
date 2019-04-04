package com.company;


import java.util.*;

// Observation: Just need to find out the ones without indegree
class MyMinimumVerticestoTraverseDirectedGraph {
    private void search(Set<Integer> res, Map<Integer, Set<Integer>> nodes,
                        int cur, int start,
                        Set<Integer> visited, Set<Integer> currVisited)
    {
        currVisited.add(cur);
        visited.add(cur);
        for (int next : nodes.get(cur)) {
            if (res.contains(next) && next != start) {
                res.remove(next);
            }
            if (!currVisited.contains(next)) {
                search(res, nodes, next, start, visited, currVisited);
            }
        }
    }


    public List<Integer> getMin(int[][] edges, int n) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        Map<Integer, Set<Integer>> reversedAdjacencyList = new HashMap<>();

        Map<Integer, Integer> indegreeMap = new HashMap<>();


        // Hashset initializations
        for (int i = 1; i <= n; i++) {
            adjacencyList.put(i, new HashSet<>());
            reversedAdjacencyList.put(i, new HashSet<>());
            indegreeMap.put(i, 0);
        }


        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            reversedAdjacencyList.get(edge[1]).add(edge[0]);
            indegreeMap.put(edge[1], indegreeMap.get(edge[1]) + 1);
        }

        Queue<Integer> noIndegreeQueue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> ret = new ArrayList<>();

        for(int key: indegreeMap.keySet()) {
            if(indegreeMap.get(key) == 0) {
                visited.add(key);
                ret.add(key);
                noIndegreeQueue.add(key);
            }
        }


//        while(!noIndegreeQueue.isEmpty()) {
//            int node = noIndegreeQueue.poll();
//            for(int neighbor: adjacencyList.get(node)) {
//                indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
//                visited.add(neighbor);
//
//                if(indegreeMap.get(neighbor) == 0) {
//                    noIndegreeQueue.add(neighbor);
//                }
//            }
//        }
        return ret;


    }
}
