package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data                                       // adds getters ,setters and toString
@NoArgsConstructor                          // adds no argument constructor
@AllArgsConstructor                         //adds all argument constructor

public class Track {
    @Id
    private int trackId;
    private String trackName;
    private String trackComments;

}
