package com.aditya.istartest.models.fakultas;

import com.aditya.istartest.db.PostgreSQLJDBC;
import com.aditya.istartest.models.BaseService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Map;

public class FakultasService implements BaseService<Fakultas> {
    private PostgreSQLJDBC conn;

    public FakultasService() throws Exception {
        conn = new PostgreSQLJDBC();
    }

    public Fakultas getById(Integer id) throws SQLException {
        PreparedStatement query = conn.prepareStatement("SELECT * FROM fakultas WHERE id=?");
        query.setInt(1, id);
        ArrayList<Map<String, String>> result = conn.executeQuery(query);

        return new Fakultas(result.get(0));
    }

    public void add(Fakultas newFakultas) throws SQLException {
        PreparedStatement query = conn.prepareStatement("INSERT INTO fakultas(nama) VALUES(?)");
        query.setString(1, newFakultas.getNama());
        conn.executeUpdate(query);
    }

    public void update(Fakultas fakultas) throws SQLException {
        PreparedStatement query = conn.prepareStatement("UPDATE fakultas SET nama=? WHERE id=?");
        query.setString(1, fakultas.getNama());
        query.setInt(2, fakultas.getId());
        conn.executeUpdate(query);
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement query = conn.prepareStatement("DELETE FROM fakultas WHERE id=?");
        query.setInt(1, id);
        conn.executeUpdate(query);
    }
}