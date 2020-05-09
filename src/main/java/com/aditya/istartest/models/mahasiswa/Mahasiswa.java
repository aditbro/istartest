package com.aditya.istartest.models.mahasiswa;

import com.aditya.istartest.models.Model;

import java.util.Map;

public class Mahasiswa extends Model {
	private Integer id;
	private String nama;
	private Integer idJurusan;
	private String namaJurusan;
	private Integer idFakultas;
	private String namaFakultas;

	public Mahasiswa() {}

	public Mahasiswa(Integer id, String nama, Integer idJurusan) {
		this.id = id;
		this.nama = nama;
		this.idJurusan = idJurusan;
	}

	public Mahasiswa(String nama, Integer idJurusan) {
		this.id = null;
		this.nama = nama;
		this.idJurusan = idJurusan;
	}

	public Mahasiswa(Map<String, String> mapData) {
		this.id = Integer.parseInt(mapData.get("id_mahasiswa"));
		this.nama = mapData.get("nama_mahasiswa");
		this.idJurusan = Integer.parseInt(mapData.get("id_jurusan"));
		this.namaJurusan = mapData.get("nama_jurusan");
		this.idFakultas = Integer.parseInt(mapData.get("id_fakultas"));
		this.namaFakultas = mapData.get("nama_fakultas");
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

	public Integer getIdJurusan() {
		return this.idJurusan;
	}

	public void setIdJurusan(Integer idJurusan) {
		this.idJurusan = idJurusan;
	}

	public String getNamaJurusan() {
		return this.namaJurusan;
	}

	public Integer getIdFakultas() {
		return this.idFakultas;
	}

	public String getNamaFakultas() {
		return this.namaFakultas;
	}
}