package ua.com.alevel.module.third;

public class Vertex {

    public String cityName;
    public boolean isInTree;

    public Vertex(String cityName) {
        this.cityName = cityName;
        this.isInTree = false;
    }

    public boolean isInTree() {
        return isInTree;
    }

    public void setInTree(boolean inTree) {
        isInTree = inTree;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}