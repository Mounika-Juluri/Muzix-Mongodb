package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService {

     Track saveTrack(Track track) throws TrackAlreadyExistsException;
     Optional<Track> displayTrackByTrackId(int id) throws TrackNotFoundException;
     Track updateCommentsOfTrack(Track track);
     void deleteTrack(int id) ;
     List<Track> getAllTracks();
     List<Track> findByName(String name) throws TrackNotFoundException;

}
