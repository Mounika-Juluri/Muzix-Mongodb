package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository                                                               //making class to be repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
    @Query("SELECT track from Track track WHERE track.trackName=?1")      //add customizable query
    List<Track> findTrackByName(String name);
}
