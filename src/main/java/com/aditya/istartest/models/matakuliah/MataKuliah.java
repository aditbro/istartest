package com.aditya.istartest.models.matakuliah;

import com.aditya.istartest.models.Model;

import java.util.Map;

public class MataKuliah extends Model {
	private Integer id;
	private String nama;

	public MataKuliah(Integer id, String nama) {
		this.id = id;
		this.nama = nama;
	}

	public MataKuliah(String nama) {
		this.id = null;
		this.nama = nama;
	}

	public MataKuliah(Map<String, String> mapData) {
		this.id = Integer.parseInt(mapData.get("id"));
		this.nama = mapData.get("nama");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
}