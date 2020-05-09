package com.aditya.istartest.models.jurusan;

import com.aditya.istartest.models.Model;

import java.util.Map;

public class Jurusan extends Model {
	private Integer id;
	private String nama;
	private Integer idFakultas;

	public Jurusan(Integer id, String nama, Integer idFakultas) {
		this.id = id;
		this.nama = nama;
		this.idFakultas = idFakultas;
	}

	public Jurusan(String nama, Integer idFakultas) {
		this.id = null;
		this.nama = nama;
		this.idFakultas = idFakultas;
	}

	public Jurusan(Map<String, String> mapData) {
		this.id = Integer.parseInt(mapData.get("id"));
		this.nama = mapData.get("nama");
		this.idFakultas = Integer.parseInt(mapData.get("id_fakultas"));
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

	public Integer getIdFakultas() {
		return this.idFakultas;
	}

	public void setIdFakultas(Integer idFakultas) {
		this.idFakultas = idFakultas;
	}
}