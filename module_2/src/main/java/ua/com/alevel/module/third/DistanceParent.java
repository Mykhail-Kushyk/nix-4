package ua.com.alevel.module.third;

public class DistanceParent {

    public int distance;
    public int parentVertex;

    public DistanceParent(int parentVertex, int distance) {
        this.distance = distance;
        this.parentVertex = parentVertex;
    }

    public int getParentVertex() {
        return parentVertex;
    }

    public void setParentVertex(int parentVertex) {
        this.parentVertex = parentVertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}