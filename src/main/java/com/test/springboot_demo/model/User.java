package com.test.springboot_demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	@Column
	private String userName;

	@Column(name = "experience")
	private int experience;

	@Column
	private String experienceLevel;

	public User() {

	}

	public User(int userid, String userName, int experience, String experienceLevel) {
		this.userid = userid;
		this.userName = userName;
		this.experience = experience;
		this.experienceLevel = experienceLevel;
	}

	public User(String userName, int experience, String experienceLevel) {
		this.userName = userName;
		this.experience = experience;
		this.experienceLevel = experienceLevel;
	}

	public User(int experience) {
		this.experience = experience;
	}

	public String getExperienceLevel() {

		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {

		this.experienceLevel = experienceLevel;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
