package com.stackroute.service;

import com.stackroute.domain.Track;
import java.util.List;
import java.util.Optional;

public interface TrackService {

     Track saveTrack(Track track);
     Optional<Track> displayTrackByTrackId(int id);
     Track updateCommentsOfTrack(Track track);
     void deleteTrack(int id);
     List<Track> getAllTracks();
     List<Track> findByName(String name);

}
