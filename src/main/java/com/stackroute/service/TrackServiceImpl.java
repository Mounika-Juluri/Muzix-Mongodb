package com.stackroute.service;

import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException{
        if(trackRepository.existsById(track.getTrackId())){

            throw new TrackAlreadyExistsException("User already exists");
        }
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public Optional<Track> displayTrackByTrackId(int id) throws TrackNotFoundException {
        Optional<Track> track=trackRepository.findById(id);
        if(!trackRepository.existsById(id)){
            throw new TrackNotFoundException("Track doesnot exist");
        }
        return trackRepository.findById(id);
    }

    @Override
    public Track updateCommentsOfTrack(Track track) {
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public void deleteTrack(int id) {

        trackRepository.deleteById(id);
    }

    @Override
    public List<Track> getAllTracks() {

        return trackRepository.findAll();
    }

    @Override
    public List<Track> findByName(String name)  {
        return trackRepository.findTrackByName(name);
    }
}
