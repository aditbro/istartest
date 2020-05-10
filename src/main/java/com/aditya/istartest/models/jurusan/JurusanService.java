package com.aditya.istartest.models.jurusan;

import com.aditya.istartest.db.PostgreSQLJDBC;
import com.aditya.istartest.models.BaseService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JurusanService implements BaseService<Jurusan> {
    private PostgreSQLJDBC conn;

    public JurusanService() throws Exception {
        conn = new PostgreSQLJDBC();
    }

    public Jurusan getById(Integer id) throws SQLException {
        PreparedStatement query = conn.prepareStatement("SELECT * FROM jurusan WHERE id=?");
        query.setInt(1, id);
        ArrayList<Map<String, String>> result = conn.executeQuery(query);

        return new Jurusan(result.get(0));
    }

    public List<Jurusan> getList() throws SQLException {
        PreparedStatement query = conn.prepareStatement(
            "SELECT id, nama, id_fakultas FROM jurusan"
        );

        ArrayList<Map<String, String>> result = conn.executeQuery(query);
        ArrayList<Jurusan> listJrs = new ArrayList<>();

        for(Map<String, String> jrs : result) {
            listJrs.add(new Jurusan(jrs));
        }

        return listJrs;
    }

    public void add(Jurusan newJurusan) throws SQLException {
        PreparedStatement query = conn.prepareStatement(
            "INSERT INTO jurusan(nama, id_fakultas) VALUES(?, ?)"
        );
        query.setString(1, newJurusan.getNama());
        query.setInt(2, newJurusan.getIdFakultas());
        conn.executeUpdate(query);
    }

    public void update(Jurusan jurusan) throws SQLException {
        PreparedStatement query = conn.prepareStatement(
            "UPDATE jurusan SET nama=?, id_fakultas=? WHERE id=?"
        );
        query.setString(1, jurusan.getNama());
        query.setInt(2, jurusan.getIdFakultas());
        query.setInt(3, jurusan.getId());
        conn.executeUpdate(query);
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement query = conn.prepareStatement("DELETE FROM jurusan WHERE id=?");
        query.setInt(1, id);
        conn.executeUpdate(query);
    }
}