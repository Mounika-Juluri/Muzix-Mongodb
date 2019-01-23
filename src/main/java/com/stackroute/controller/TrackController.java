package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@ControllerAdvice
@RequestMapping(value="/api/v1")
public class TrackController {

    private TrackService trackService;
    //globalException for TrackalreadyExistsException
    @ExceptionHandler(value=TrackAlreadyExistsException.class)
    public ResponseEntity <?> trackAlreadyExistsException(final TrackAlreadyExistsException e) {
        ResponseEntity responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_MODIFIED);
        return responseEntity;
    }
    @ExceptionHandler(value=TrackNotFoundException.class)
    public ResponseEntity <?> trackNotFoundException(final TrackNotFoundException e) {
        ResponseEntity responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_MODIFIED);
        return responseEntity;
    }

   @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    //addition of track
    @ApiOperation(value = "Add Track", response = Iterable.class)
    @PostMapping("/track")
        public ResponseEntity<?> saveTrack(@RequestBody Track track)throws TrackAlreadyExistsException{
        ResponseEntity responseEntity;
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        return responseEntity;
    }
    //getting all the tracks
    @ApiOperation(value = "Get All Tracks", response = Iterable.class)
    @GetMapping("/tracks")
       public ResponseEntity<?> getAllTracks() throws TrackNotFoundException{
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    //view track by id
    @ApiOperation(value = "View Track By ID", response = Iterable.class)
    @GetMapping("/track/{id}")
    public ResponseEntity<Optional<Track>> displayById(@PathVariable int id) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        try{
            responseEntity=new ResponseEntity<Optional<Track>>(trackService.displayTrackByTrackId(id),HttpStatus.OK);
        }catch(TrackNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
    //view track by name
    @ApiOperation(value = "View Track By Name", response = Iterable.class)
    @GetMapping("/tracks/{name}")
    public ResponseEntity<List<Track>> findTrackByName(@PathVariable String name) throws TrackNotFoundException{
        ResponseEntity responseEntity;
            responseEntity=new ResponseEntity<List<Track>>(trackService.findByName(name),HttpStatus.OK);
        return responseEntity;
    }
    //delete track
    @ApiOperation(value = "Delete Track", response = Iterable.class)
    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        trackService.deleteTrack(id);
        responseEntity=new ResponseEntity<String>("Successfully deleted", HttpStatus.NO_CONTENT);
        return responseEntity;
    }
    //update track
    @ApiOperation(value = "Update Track", response = Iterable.class)
    @PutMapping("/track/{id}/{comments}")
    public ResponseEntity<?> Updatetrack(@PathVariable int id,@PathVariable String comments) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        trackService.updateCommentsOfTrack(id,comments);
        responseEntity=new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        return responseEntity;
    }
}
