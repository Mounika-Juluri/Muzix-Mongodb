package com.stackroute.service;

import com.stackroute.domain.Track;
import java.util.List;
import java.util.Optional;

public interface TrackService {

    public Track saveTrack(Track track);
    public Optional<Track> displayTrackByTrackId(int id);
    public Track updateCommentsOfTrack(Track track);
    public void deleteTrack(int id);
    public List<Track> getAllTracks();
}
