package com.mindtree.emp.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mindtree.emp.entity.Mind;
import com.mindtree.emp.entity.Track;

public interface TrackRepo extends JpaRepository<Track, Integer> {
	List<Mind> findByTrackId(int id);
	Track findBytrackName(String name);
}
