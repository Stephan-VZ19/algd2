package ch.fhnw.algd2.graphedit;

import java.util.*;
import java.util.stream.Collectors;

import ch.fhnw.algd2.graphedit.GraphAlgorithms.DFS;
import ch.fhnw.algd2.graphedit.GraphAlgorithms.TopSort;

public final class AdjListGraphMichi<K> extends AbstractGraph<K> implements TopSort, DFS<K> {


	private static class Vertex<K> {
		final K id;
		boolean visited;

		final List<Vertex<K>> adjList = new ArrayList<>();

		Vertex(K vertex) {
			id = Objects.requireNonNull(vertex);
		}

		boolean addEdgeTo(Vertex<K> to) {
			if (adjList.contains(to)) {
				return false;
			}
			adjList.add(to);
			return true;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Vertex<?> vertex = (Vertex<?>) o;
			return Objects.equals(id, vertex.id);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
	}

	private final Map<K, Vertex<K>> vertices = new HashMap<>();
	private int numEdges = 0;

	public AdjListGraphMichi(boolean directed) {
		super(directed);
	}

	public AdjListGraphMichi() { // default constructor
		this(false);
	}

	public AdjListGraphMichi(AdjListGraphMichi<K> orig) {
		this(orig.isDirected());
		this.numEdges = orig.numEdges;
		orig.vertices.keySet().forEach(this::addVertex);
		for (var v : orig.vertices.values()) {
			for (var w : v.adjList) {
				addEdge(v.id, w.id);
			}
		}
	}

	public boolean addVertex(K id) {
		if (id != null && !vertices.containsKey(id)) {
			vertices.put(id, new Vertex<>(id));
			return true;
		} else {
			return false;
		}
	}

	public boolean addEdge(K fromId, K toId) {
		var from = vertices.get(fromId);
		var to = vertices.get(toId);
		if (from != null && to != null && from.addEdgeTo(to)) {
			if (!isDirected()) {
				to.addEdgeTo(from);
			}
			++numEdges;
			return true;
		} else {
			return false;
		}
	}

	public boolean removeEdge(K fromId, K toId) {
		var from = vertices.get(fromId);
		var to = vertices.get(toId);
		if (from != null && to != null && from.adjList.remove(to)) {
			if (!isDirected()) {
				to.adjList.remove(from);
			}
			--numEdges;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getNofVertices() {
		return vertices.size();
	}

	@Override
	public int getNofEdges() {
		return numEdges;
	}

	public Set<K> getVertices() {
		return new HashSet<>(vertices.keySet());
	}

	public Set<K> getAdjacentVertices(K id) {
		var vertex = vertices.get(id);
		if (vertex == null) {
			return Set.of();
		}

		return vertex.adjList.stream()
				.map(v -> v.id)
				.collect(Collectors.toSet());
	}

	@Override
	public Object clone() {
		return new AdjListGraphMichi<>(this);
	}

	@Override
	public void sort() {
		if (!isDirected()) {
			System.out.println("Graph is not directed. No topological order!");
			return;
		}

		// id -> indegree
		var indegrees = calculateIndegrees();
		var zeroIndegrees = indegrees.entrySet().stream()
				.filter(e -> e.getValue() == 0)
				.map(Map.Entry::getKey)
				.collect(Collectors.toCollection(ArrayDeque::new));

		var topOrder = new ArrayList<>(indegrees.size());
		while (!zeroIndegrees.isEmpty()) {
			K next = zeroIndegrees.removeFirst();
			topOrder.add(next);
			for (K target : getAdjacentVertices(next)) {
				int indegree = indegrees.get(target);
				if (indegree == 1) {
					zeroIndegrees.addLast(target);
				} else {
					indegrees.put(target, indegree - 1);
				}
			}
		}

		if (topOrder.size() == getNofVertices()) {
			System.out.println("Topological Order: " + topOrder.toString());
		} else {
			System.out.println("No Topological Order, Graph contains cycles!");
		}
	}

	private Map<K, Integer> calculateIndegrees() {
		var indegrees = new HashMap<K, Integer>();
		for (K source : getVertices()) {
			// Necessary for zero indegree nodes
			indegrees.putIfAbsent(source, 0);
			this.getAdjacentVertices(source).forEach(target ->
					indegrees.merge(target, 1, Integer::sum)
			);
		}
		return indegrees;
	}

	@Override
	public Graph<K> traverse(K startVertex) {
		var spanningTree = new AdjListGraph<K>(isDirected());
		vertices.values().forEach(v -> v.visited = false);
		spanningTree.addVertex(startVertex);
		dfs(vertices.get(startVertex), spanningTree);
		return spanningTree;
	}

	private void dfs(Vertex<K> vertex, AdjListGraph<K> spanningTree) {
		if (!vertex.visited) {
			vertex.visited = true;
			for (var neighbour : vertex.adjList) {
				if (!neighbour.visited) {
					spanningTree.addVertex(neighbour.id);
					spanningTree.addEdge(vertex.id, neighbour.id);
					dfs(neighbour, spanningTree);
				}
			}
		}
	}
}