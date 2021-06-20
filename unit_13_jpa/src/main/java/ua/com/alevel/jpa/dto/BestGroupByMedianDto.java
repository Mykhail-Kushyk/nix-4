package ua.com.alevel.jpa.dto;

import lombok.Getter;

@Getter
public class BestGroupByMedianDto {

    private Long id;
    private String name;
    private String courseName;

    public BestGroupByMedianDto(Long id, String name, String courseName) {
        this.id = id;
        this.courseName = courseName;
        this.name = name;
    }
}
