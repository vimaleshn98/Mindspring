package com.mindtree.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindtree.emp.entity.Mind;
import com.mindtree.emp.entity.Track;
import com.mindtree.emp.exeception.controller.TrackNotFound;
import com.mindtree.emp.exeception.service.MindNotFoundService;
import com.mindtree.emp.exeception.service.TrackNotFoundService;
import com.mindtree.emp.util.MindRepo;
import com.mindtree.emp.util.TrackRepo;
@Service
public class TrackService {
	
	@Autowired
	MindRepo minds;
	
	@Autowired
	TrackRepo tracks; 
	
	public ResponseEntity<List<Track>> getTracks() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Track>>(tracks.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<String> addTrack(Track trck) {
		// TODO Auto-generated method stub
		tracks.save(trck);
		return new ResponseEntity<>("Track is Added", HttpStatus.CREATED);
	}

	public ResponseEntity<Track> getTrackById(int id) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(tracks.findById(id).get(), HttpStatus.OK);
	}

	public ResponseEntity<Track> getTrackByName(String name) {
		// TODO Auto-generated method stub
		Track tr = tracks.findBytrackName(name);
		if(tr==null) {
			throw new TrackNotFoundService("Track Not Found");
		}else
		return new ResponseEntity<Track>(tr, HttpStatus.OK);
	}

	public ResponseEntity<String> deleteById(int id) {
		// TODO Auto-generated method stub
		Track deleteTrack =tracks.findById(id).orElseThrow( ()->new TrackNotFoundService("Track not found"));
		tracks.delete(deleteTrack);
		return new ResponseEntity<>("Track deleted successfully", HttpStatus.NO_CONTENT);
	}


	public ResponseEntity<String> updateName(String name, int id) {
		// TODO Auto-generated method stub
		Track updatedTrack = tracks.findById(id).orElseThrow( ()->new TrackNotFoundService("Track not found"));
		updatedTrack.setTrackName(name);
		tracks.save(updatedTrack);
		return new ResponseEntity<>("name updated successfully", HttpStatus.OK);
	}

}
