package com.aditya.istartest.models;

public class Fakultas {
	private Integer id;
	private String name;

	public Fakultas(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}