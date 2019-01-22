package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="api/v1/")
public class TrackController {
    TrackService trackService;
    //@Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @PostMapping("track")
        public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }catch(Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("track")
       public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @GetMapping("track/{id}")
    public ResponseEntity<Optional<Track>> displayById(@PathVariable int id){

        return new ResponseEntity<Optional<Track>>(trackService.displayTrackByTrackId(id),HttpStatus.OK);

    }
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        ResponseEntity responseEntity;
        trackService.deleteTrack(id);
        responseEntity=new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        return responseEntity;
    }
    @PutMapping("track")
    public ResponseEntity<?> UpdateTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        trackService.updateCommentsOfTrack(track);
        responseEntity=new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        return responseEntity;
    }
}
