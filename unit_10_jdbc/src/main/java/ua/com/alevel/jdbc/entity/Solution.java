package ua.com.alevel.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Solution {

    private int problemId;
    private int fromId;
    private int toId;
    private int cost;
}