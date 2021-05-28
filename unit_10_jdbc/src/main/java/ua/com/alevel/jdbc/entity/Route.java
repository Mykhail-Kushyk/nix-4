package ua.com.alevel.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Route {

    private int id;
    private int fromId;
    private int toId;
    private int cost;
}