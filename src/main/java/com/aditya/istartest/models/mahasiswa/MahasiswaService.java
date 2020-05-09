package com.aditya.istartest.models.mahasiswa;

import com.aditya.istartest.db.PostgreSQLJDBC;
import com.aditya.istartest.models.BaseService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Map;

public class MahasiswaService implements BaseService<Mahasiswa> {
    private PostgreSQLJDBC conn;

    public MahasiswaService() throws Exception {
        conn = new PostgreSQLJDBC();
    }

    public Mahasiswa getById(Integer id) throws SQLException {
        PreparedStatement query = conn.prepareStatement(
            "SELECT mahasiswa.nama as nama_mahasiswa, " +
            "mahasiswa.id as id_mahasiswa, " +
            "jurusan.id as id_jurusan, " +
            "jurusan.nama as nama_jurusan, " + 
            "fakultas.id as id_fakultas, " + 
            "fakultas.nama as nama_fakultas " +
            "FROM mahasiswa JOIN jurusan " + 
            "ON mahasiswa.id_jurusan=jurusan.id " + 
            "JOIN fakultas " + 
            "ON jurusan.id_fakultas=fakultas.id " +
            "WHERE mahasiswa.id=?"
        );
        query.setInt(1, id);
        ArrayList<Map<String, String>> result = conn.executeQuery(query);

        return new Mahasiswa(result.get(0));
    }

    public ArrayList<Mahasiswa> getList(Integer page) throws SQLException {
        Integer dataPerPage = 10;
        Integer offset = (page - 1) * dataPerPage;
        
        PreparedStatement query = conn.prepareStatement(
            "SELECT mahasiswa.nama as nama_mahasiswa, " +
            "mahasiswa.id as id_mahasiswa, " +
            "jurusan.id as id_jurusan, " +
            "jurusan.nama as nama_jurusan, " + 
            "fakultas.id as id_fakultas, " + 
            "fakultas.nama as nama_fakultas " +
            "FROM mahasiswa JOIN jurusan " + 
            "ON mahasiswa.id_jurusan=jurusan.id " + 
            "JOIN fakultas " + 
            "ON jurusan.id_fakultas=fakultas.id " + 
            "ORDER BY mahasiswa.id LIMIT ? OFFSET ?"
        );
        query.setInt(1, dataPerPage);
        query.setInt(2, offset);

        ArrayList<Map<String, String>> result = conn.executeQuery(query);
        ArrayList<Mahasiswa> listMhs = new ArrayList<>();

        for(Map<String, String> mhs : result) {
            listMhs.add(new Mahasiswa(mhs));
        }

        return listMhs;
    }

    public void add(Mahasiswa newMahasiswa) throws SQLException {
        PreparedStatement query = conn.prepareStatement(
            "INSERT INTO mahasiswa(nama, id_jurusan) VALUES(?, ?)"
        );
        query.setString(1, newMahasiswa.getNama());
        query.setInt(2, newMahasiswa.getIdJurusan());
        conn.executeUpdate(query);
    }

    public void update(Mahasiswa mahasiswa) throws SQLException {
        PreparedStatement query = conn.prepareStatement(
            "UPDATE mahasiswa SET nama=?, id_jurusan=? WHERE id=?"
        );
        query.setString(1, mahasiswa.getNama());
        query.setInt(2, mahasiswa.getIdJurusan());
        query.setInt(3, mahasiswa.getId());
        conn.executeUpdate(query);
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement query = conn.prepareStatement("DELETE FROM mahasiswa WHERE id=?");
        query.setInt(1, id);
        conn.executeUpdate(query);
    }
}