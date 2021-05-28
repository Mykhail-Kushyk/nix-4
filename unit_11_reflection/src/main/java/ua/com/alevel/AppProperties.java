package ua.com.alevel;

import lombok.Getter;

@Getter
public class AppProperties {

    @PropertyKey("connections.limit")
    public int maxConnections;

    @PropertyKey("application.name")
    public String appName;

    @PropertyKey("variables.limit")
    public int maxVariables;

    @PropertyKey("application.timeout")
    public double timeout;

    @PropertyKey("application.classes")
    public int totalClasses;
}