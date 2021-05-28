package ua.com.alevel.module.third;

public class Graph {

    private final int INFINITY = 1000000;
    private Vertex[] vertexList;
    private int[][] adjMatrix;
    private int totalVerts;
    private int currentTotalVerts;
    private int nTree;
    private DistanceParent[] sPath;
    private int currentVert;
    private int startToCurrentDistance;

    public int getTotalVerts() {
        return totalVerts;
    }

    public Graph(int totalVerts) {
        this.totalVerts = totalVerts;
        this.currentTotalVerts = 0;
        this.vertexList = new Vertex[totalVerts];
        this.adjMatrix = new int[totalVerts][totalVerts];
        this.nTree = 0;
        for (int i = 0; i < totalVerts; i++) {
            for (int j = 0; j < totalVerts; j++) {
                adjMatrix[i][j] = INFINITY;
            }
        }
        this.sPath = new DistanceParent[totalVerts];
    }

    public void addVertex(String cityName) {
        vertexList[currentTotalVerts++] = new Vertex(cityName);
    }

    public void addEdge(int start, int end, int weight) {
        start--;
        end--;
        adjMatrix[start][end] = weight;
        adjMatrix[end][start] = weight;
    }

    public int findShortestPathBetweenCities(String firstCity, String secondCity) {
        int startTree = indexOfCity(firstCity);
        int endTree = indexOfCity(secondCity);
        findShortestPath(startTree);
        return sPath[endTree].distance;
    }

    public int findShortestPathBetweenCities(int firstCity, int secondCity) {
        int startTree = --firstCity;
        int endTree = --secondCity;
        findShortestPath(startTree);
        return sPath[endTree].distance;
    }

    private int indexOfCity(String city) {
        for (int i = 0; i < totalVerts; i++) {
            if (vertexList[i].getCityName().equals(city)) {
                return i;
            }
        }
        return -1;
    }

    private void findShortestPath(int startTree) {
        vertexList[startTree].isInTree = true;
        nTree = 1;

        for (int i = 0; i < totalVerts; i++) {
            int tempDistance = adjMatrix[startTree][i];
            sPath[i] = new DistanceParent(startTree, tempDistance);
        }

        while (nTree < totalVerts) {
            int indexMin = getMin();
            int minDistance = sPath[indexMin].distance;
            if (minDistance == INFINITY) {
                break;
            } else {
                currentVert = indexMin;
                startToCurrentDistance = sPath[indexMin].distance;
            }

            vertexList[currentVert].isInTree = true;
            nTree++;
            adjustsPath(startTree);
        }

        nTree = 0;
        for (int i = 0; i < totalVerts; i++) {
            vertexList[i].isInTree = false;
        }
    }

    private int getMin() {
        int minDistance = INFINITY;
        int indexMin = 0;
        for (int i = 1; i < totalVerts; i++) {
            if (!vertexList[i].isInTree && sPath[i].distance < minDistance) {
                minDistance = sPath[i].distance;
                indexMin = i;
            }
        }
        return indexMin;
    }

    private void adjustsPath(int startTree) {
        int column = 1;
        while (column < totalVerts) {
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            int currentToFringe = adjMatrix[currentVert][column];
            int startToFringe = startToCurrentDistance + currentToFringe;
            int sPathDistance = sPath[column].distance;

            if (startToFringe < sPathDistance) {
                sPath[column].parentVertex = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }
}