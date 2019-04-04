package com.company;

import java.util.*;

public class AlienDictionary {
    public String findOrder(String[] dictionary) {
        if(dictionary.length < 2) {
            return dictionary.length == 0? "": dictionary[0];
        }

        List<char[]> dependencies = new ArrayList();


        for(int i=1; i< dictionary.length; i++) {
            char[] dependency = findDependency(dictionary, i);
            if(dependency.length == 0) {
                continue;
            }
            dependencies.add(dependency);
        }

        Map<Character, Integer> indegreeMap = createIndegreeMap(dependencies);
        Map<Character, List<Character>> adjacencyList = createAdjacencyList(dependencies);


        Queue<Character> queue = new LinkedList();
        int notVisited = 0;
        StringBuilder sb = new StringBuilder();

        for(char character: indegreeMap.keySet()) {
            if(indegreeMap.get(character) == 0) {
                queue.offer(character);
            } else {
                notVisited ++;
            }
        }

        while(!queue.isEmpty()) {
            char top = queue.poll();

            for(char neighbor: adjacencyList.get(top)) {
                    indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
                    if(indegreeMap.get(neighbor) == 0) {
                        queue.offer(neighbor);
                        notVisited --;
                    }
            }

            sb.append(top);
        }
        if(notVisited > 0) return "";
        return sb.toString();
    }

    private Map<Character, List<Character>> createAdjacencyList(List<char[]> dependencyList) {
        Map<Character, List<Character>> map = new HashMap<>();

        for(char [] dependency: dependencyList) {
            char from = dependency[0];
            char to = dependency[1];

            if(!map.containsKey(to)) {
                List<Character> list = new ArrayList<>();
                map.put(to, list);
            }

            if(map.containsKey(from)) {
                map.get(from).add(to);
            } else {
                List<Character> list = new ArrayList<>();
                list.add(to);
                map.put(from, list);
            }
        }

        return map;
    }

    private Map<Character, Integer> createIndegreeMap(List<char[]> dependencyList) {
        Map<Character, Integer> map = new HashMap<>();

        for(char [] dependency: dependencyList) {
            char to = dependency[1];
            char from = dependency[0];

            if(!map.containsKey(from)) {
                map.put(from, 0);
            }

            if(map.containsKey(to)) {
                map.put(to, map.get(to) + 1);
            } else {
                map.put(to, 1);
            }
        }

        return map;
    }

    private char[] findDependency (String[] dictionary, int pos) {

        for(int i=0; i<dictionary[pos-1].length(); i++) {
            if(dictionary[pos].charAt(i) != dictionary[pos-1].charAt(i)) {
                return new char[]{dictionary[pos-1].charAt(i), dictionary[pos].charAt(i)};
            }
        }
        return new char[]{};
    }
}
