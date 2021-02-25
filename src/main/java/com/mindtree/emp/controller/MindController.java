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
import com.mindtree.emp.exeception.controller.MindNotFound;
import com.mindtree.emp.exeception.controller.TrackNotFound;
import com.mindtree.emp.exeception.service.MindNotFoundService;
import com.mindtree.emp.exeception.service.TrackNotFoundService;
import com.mindtree.emp.service.MindService;
import com.mindtree.emp.util.TrackRepo;
import com.sun.istack.NotNull;

@RestController
public class MindController {
	@Autowired
	MindService mind;

	@GetMapping("/mind")
	public ResponseEntity<List<Mind>> getTrack() {
		return mind.getMinds();
	}

	@PostMapping(value = "/mind/{value}")
	public ResponseEntity<String> addTrack(@RequestBody Mind mindsp, @PathVariable("value") int id) {
		try {
			return mind.addMind(mindsp, id);
		} catch (TrackNotFoundService e) {
			// TODO: handle exception
			throw new TrackNotFound(e.getLocalizedMessage());
		}
	}

	@GetMapping("/mind/{value}")
	public ResponseEntity<Mind> getMindById(@PathVariable("value") @NotNull int id) {
		try {
			return mind.getMindById(id);
		} catch (MindNotFoundService e) {
			// TODO: handle exception
			throw new MindNotFound(e.getLocalizedMessage());
		}
	}

	@DeleteMapping("/mind/{value}")
	public ResponseEntity<String> delete(@PathVariable("value") int id) {
		try {
			return mind.deleteById(id);
		} catch (MindNotFoundService e) {
			// TODO: handle exception
			throw new MindNotFound(e.getLocalizedMessage());
		}

	}

	@PutMapping("/mind/{value}")
	public ResponseEntity<String> updateName(@RequestBody String name, @PathVariable("value") int id) {
		try {
		return mind.updateName(name, id);
		}catch (MindNotFoundService e) {
			// TODO: handle exception
			throw new MindNotFound(e.getLocalizedMessage());
		}
	}
}
