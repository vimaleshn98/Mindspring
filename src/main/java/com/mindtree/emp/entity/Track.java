package com.mindtree.emp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class Track {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int trackId;
	private String trackName;
	
	@OneToMany(mappedBy = "track" , cascade = {CascadeType.ALL})
	
	@JsonBackReference
	private List<Mind> minds;
	
	

	public void setMinds(List<Mind> minds) {
		this.minds = minds;
	}

	public Track() {
		// TODO Auto-generated constructor stub
	}

	public Track(String trackName) {
		super();
		this.trackName = trackName;
	}



	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", trackName=" + trackName + "]";
	}
	
	
}
