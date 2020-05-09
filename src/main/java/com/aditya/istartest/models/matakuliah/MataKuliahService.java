package com.aditya.istartest.models.matakuliah;

import com.aditya.istartest.db.PostgreSQLJDBC;
import com.aditya.istartest.models.BaseService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Map;

public class MataKuliahService implements BaseService<MataKuliah> {
    private PostgreSQLJDBC conn;

    public MataKuliahService() throws Exception {
        conn = new PostgreSQLJDBC();
    }

    public MataKuliah getById(Integer id) throws SQLException {
        PreparedStatement query = conn.prepareStatement("SELECT * FROM matakuliah WHERE id=?");
        query.setInt(1, id);
        ArrayList<Map<String, String>> result = conn.executeQuery(query);

        return new MataKuliah(result.get(0));
    }

    public void add(MataKuliah newMataKuliah) throws SQLException {
        PreparedStatement query = conn.prepareStatement("INSERT INTO matakuliah(nama) VALUES(?)");
        query.setString(1, newMataKuliah.getNama());
        conn.executeUpdate(query);
    }

    public void update(MataKuliah matakuliah) throws SQLException {
        PreparedStatement query = conn.prepareStatement("UPDATE matakuliah SET nama=? WHERE id=?");
        query.setString(1, matakuliah.getNama());
        query.setInt(2, matakuliah.getId());
        conn.executeUpdate(query);
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement query = conn.prepareStatement("DELETE FROM matakuliah WHERE id=?");
        query.setInt(1, id);
        conn.executeUpdate(query);
    }
}