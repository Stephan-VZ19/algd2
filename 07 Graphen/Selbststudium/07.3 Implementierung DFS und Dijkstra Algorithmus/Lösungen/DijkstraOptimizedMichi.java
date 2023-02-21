package ch.fhnw.algd2.graphedit;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DijkstraOptimizedMichi {
    static <K> Graph<K> getShortestPath(WeightedGraph<K> g, K from, K to) {
        // Assume: from and to must be different nodes
        if (from == to)
            throw new IllegalArgumentException(
                    "From and to must be different");

        // Initialize Distance Map for each node
        var distances = g.getVertices().stream()
                .collect(Collectors.toMap(Function.identity(), DijkstraInfo::new));

        var start = distances.get(from);
        start.via = null;
        start.distToSource = 0;

        // Using a Treeset with custom comparison function.
        // Looking for the next node with min total distance is now O(log(n))
        var reachableNodes = new TreeSet<K>((v, w) -> {
            DijkstraInfo<K> vInfo = distances.get(v);
            DijkstraInfo<K> wInfo = distances.get(w);
            if (vInfo.distToSource < wInfo.distToSource)
                return -1;
            else if (vInfo.distToSource > wInfo.distToSource)
                return 1;
            else
                return vInfo.id.toString().compareTo(wInfo.id.toString());
        });

        reachableNodes.add(from);
        var found = false;

        while (!found && reachableNodes.size() > 0) {
            var min = getNodeWithMinDistToSource(reachableNodes, distances);
            for (K adj : g.getAdjacentVertices(min.id)) {
                var dist = g.getEdgeWeight(min.id, adj);
                // Set new shortest path to next node
                if (min.distToSource + dist < distances.get(adj).distToSource) {
                    var nextInfos = distances.get(adj);
                    nextInfos.via = min.id;
                    nextInfos.distToSource = min.distToSource + dist;
                    reachableNodes.add(nextInfos.id);
                }
            }
            reachableNodes.remove(min.id);
            if (min.id.equals(to)) {
                found = true;
            }
        }

        if (!found) {
            System.out.printf("There was no path found from %s to %s", from, to);
            return g;
        } else {
            return createPathToTarget(g, distances, to);
        }
    }

    private static <K> DijkstraInfo<K> getNodeWithMinDistToSource(
            TreeSet<K> reachableNodes, Map<K, DijkstraInfo<K>> distances) {
        if (reachableNodes.isEmpty()) {
            throw new IllegalStateException("There are no reachable nodes.");
        }

        return distances.get(reachableNodes.first());
    }

    private static <K> WeightedGraph<K> createPathToTarget(
            WeightedGraph<K> g,
            Map<K, DijkstraInfo<K>> distances,
            K to) {
        var spanningTree = new WeightedGraphImpl<K>(g.isDirected());
        K target = to;
        spanningTree.addVertex(target);
        K source = distances.get(target).via;
        while (source != null) {
            spanningTree.addVertex(source);
            spanningTree.addEdge(source, target, g.getEdgeWeight(source, target));
            target = source;
            source = distances.get(target).via;
        }

        return spanningTree;
    }

    private static class DijkstraInfo<K> {
        private final K id;
        private double distToSource = Double.MAX_VALUE;
        private K via = null;

        private DijkstraInfo(K id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "DijkstraInfo [element=" + id + ", distToSource="
                    + distToSource + ", via=" + via + "]";
        }

    }
}