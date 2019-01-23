package com.stackroute.service;

import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
//Service class implementing methods from track service interface
@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;
    //constructor for service class takes trackrepository as argument
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //method to save track to track repository throws global exception if track already exists
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException{
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("User already exists");
        }
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

    //method to display a track by getting trackId throws not found exception if track doesnt exist
    @Override
    public Optional<Track> displayTrackByTrackId(int id) throws TrackNotFoundException {
        Optional<Track> track=trackRepository.findById(id);
        if(!trackRepository.existsById(id)){
            throw new TrackNotFoundException("Track doesnot exist");
        }
        return trackRepository.findById(id);
    }

    //method to update a track
    @Override
    public Track updateCommentsOfTrack(int id,String comments) throws TrackNotFoundException {
        Optional<Track> track=trackRepository.findById(id);
        if(!trackRepository.existsById(id)){
            throw new TrackNotFoundException("Track doesnot exist");
        }
        Track track1=track.get();
        track1.setTrackComments(comments);
        Track savedTrack=trackRepository.save(track1);
        return savedTrack;
    }

    //method to delete the track getting trackId
    @Override
    public void deleteTrack(int id) throws TrackNotFoundException {
        if(!trackRepository.existsById(id)){
            throw new TrackNotFoundException("Track doesnot exist");
        }
        trackRepository.deleteById(id);
    }

    //method to get all the tracks
    @Override
    public List<Track> getAllTracks()throws TrackNotFoundException {
        if(trackRepository.findAll().isEmpty()) {
            throw new TrackNotFoundException("No Track Found");
        }
        return trackRepository.findAll();
    }

    //method to find a track by its name using @query
    @Override
    public List<Track> findByName(String name) throws TrackNotFoundException {
        if (trackRepository.findTrackByName(name).isEmpty()) {
            throw new TrackNotFoundException("Track not found");
        }
        return trackRepository.findTrackByName(name);
    }
}
