package com.mindtree.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.emp.entity.Mind;
import com.mindtree.emp.entity.Track;
import com.mindtree.emp.exeception.controller.TrackNotFound;
import com.mindtree.emp.exeception.service.TrackNotFoundService;
import com.mindtree.emp.service.TrackService;
import com.sun.istack.NotNull;

@RestController
public class TrackController {
	@Autowired
	TrackService trackService;

	@GetMapping("/track")
	public ResponseEntity<List<Track>> getTrack() {
		return trackService.getTracks();
	}

	@PostMapping(value = "/track")
	public ResponseEntity<String> addTrack(@RequestBody Track classroom) {

		return trackService.addTrack(classroom);
	}

	@GetMapping("/track/{value}")
	public ResponseEntity<Track> getTrackById(@PathVariable("value") @NotNull String name) {
		try {
			return trackService.getTrackByName(name);
		} catch (TrackNotFoundService e) {
			// TODO: handle exception
			throw new TrackNotFound(e.getLocalizedMessage());
		}
	}


	@DeleteMapping("/track/{value}")
	public ResponseEntity<String> deleteTrack(@PathVariable("value") int id) {
		try {
			return trackService.deleteById(id);
		} catch (TrackNotFoundService e) {
			// TODO: handle exception
			throw new TrackNotFound(e.getLocalizedMessage());
		}

	}

	@PutMapping("/track/{value}")
	public ResponseEntity<String> updateName(@RequestBody String name, @PathVariable("value") int id) {
		try {
			return trackService.updateName(name, id);
		} catch (TrackNotFoundService e) {
			// TODO: handle exception
			throw new TrackNotFound(e.getLocalizedMessage());
		}
	}

}
