package com.aekc.algorithm.graph.weight;

import com.aekc.algorithm.graph.AbstractGraph;

public class WeightedEdge extends AbstractGraph.Edge {

    public double weight;

    public WeightedEdge(int u, int v, double weight) {
        super(u, v);
        this.weight = weight;
    }

    public int compareTo(WeightedEdge edge) {
        return Double.compare(weight, edge.weight);
    }
}
