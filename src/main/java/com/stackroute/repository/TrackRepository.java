package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository                                                               //making class to be repository
public interface TrackRepository extends MongoRepository<Track,Integer> {
}
