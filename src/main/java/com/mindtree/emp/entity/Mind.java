package com.mindtree.emp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class Mind {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stuId;
	private String stuName;
	@ManyToOne()
	@JoinColumn(name = "track_id")
	@JsonManagedReference
	Track track;
	
	public Mind(int stuId, String stuName, Track track) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.track = track;
	}

	public Mind() {
		// TODO Auto-generated constructor stub
	}

	public Mind(int stuId, String stuName) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
	}
	
	public Mind(String stuName) {
		super();
		this.stuName = stuName;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	@Override
	public String toString() {
		return "Mind [MindId=" + stuId + ", MindName=" + stuName + ", track=" + track + "]";
	}
	
	
	

}
