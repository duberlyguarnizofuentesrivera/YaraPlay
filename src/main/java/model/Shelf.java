package model;

import lombok.Data;

@Data
public class Shelf {
    private long id;
    private int capacity;
    private int floor;
    private int hallway;
    private int level;

}
