package com.mindtree.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindtree.emp.entity.Mind;
import com.mindtree.emp.entity.Track;
import com.mindtree.emp.exeception.service.MindNotFoundService;
import com.mindtree.emp.exeception.service.TrackNotFoundService;
import com.mindtree.emp.util.MindRepo;
import com.mindtree.emp.util.TrackRepo;
@Service
public class MindService {
	@Autowired
	MindRepo minds; 
	
	@Autowired
	TrackRepo trads;
	
	public ResponseEntity<List<Mind>> getMinds() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Mind>>(minds.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<String> addMind(Mind mindr, int id) {
		// TODO Auto-generated method stub
		Track tra = trads.findById(id).get();
		if(tra==null) {
			throw new TrackNotFoundService("Track Not Found");
		}else {
		mindr.setTrack(tra);
		minds.save(mindr);
		return new ResponseEntity<>("Mind  is Added", HttpStatus.CREATED);
		}
	}

	public ResponseEntity<Mind> getMindById(int id) {
		// TODO Auto-generated method stub
		Mind M = minds.findById(id).orElseThrow(() -> new MindNotFoundService("mind not found"));
		return new ResponseEntity<>(M, HttpStatus.OK);
	}

	public ResponseEntity<String> deleteById(int id) {
		Mind deleteMind =minds.findById(id).orElseThrow( ()->new MindNotFoundService("Mind not found"));
		minds.delete(deleteMind);
		return new ResponseEntity<>("Employee deleted successfully", HttpStatus.NO_CONTENT);
	}

	

	public ResponseEntity<String> updateName(String name, int id) {
		// TODO Auto-generated method stub
		Mind updatedMind = minds.findById(id).orElseThrow( ()->new MindNotFoundService("Mind not found"));
		updatedMind.setStuName(name);
		minds.save(updatedMind);
		return new ResponseEntity<>("name updated successfully", HttpStatus.OK);
	}

}
