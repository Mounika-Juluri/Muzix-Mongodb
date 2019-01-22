package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/track")
public class TrackController {
    TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @ApiOperation(value = "Add Track", response = Iterable.class)

    @PostMapping("/add")
        public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }catch(TrackAlreadyExistsException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Get All Tracks", response = Iterable.class)

    @GetMapping("/getAll")
       public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @ApiOperation(value = "View Track By ID", response = Iterable.class)

    @GetMapping("/show/{id}")
    public ResponseEntity<Optional<Track>> displayById(@PathVariable int id){
        ResponseEntity responseEntity;
        try{
            responseEntity=new ResponseEntity<Optional<Track>>(trackService.displayTrackByTrackId(id),HttpStatus.OK);
        }catch(TrackNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @ApiOperation(value = "View Track By Name", response = Iterable.class)

    @GetMapping("/shows/{name}")
    public ResponseEntity<List<Track>> findTrackByName(@PathVariable String name){
        ResponseEntity responseEntity;
        try{
            responseEntity=new ResponseEntity<List<Track>>(trackService.findByName(name),HttpStatus.OK);
        }catch(TrackNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Delete Track", response = Iterable.class)

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        ResponseEntity responseEntity;
        trackService.deleteTrack(id);
        responseEntity=new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        return responseEntity;
    }
    @ApiOperation(value = "Update Track", response = Iterable.class)

    @PutMapping("/update")
    public ResponseEntity<?> Updatetrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        trackService.updateCommentsOfTrack(track);
        responseEntity=new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        return responseEntity;
    }
}
