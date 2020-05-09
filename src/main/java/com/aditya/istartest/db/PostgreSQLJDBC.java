package com.aditya.istartest.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;

public class PostgreSQLJDBC {
    private Connection conn;

    private String dbHost;
    private String dbPort;
    private String dbUsername;
    private String dbPassword;
    private String dbName;

    public PostgreSQLJDBC() throws Exception {
        this.loadConfig();
        this.connect();
    }

    public Integer executeUpdate(PreparedStatement stmt) throws SQLException {
        Integer affectedRows = stmt.executeUpdate();
        stmt.close();

        return affectedRows;
    }

    public ArrayList<Map<String, String>> executeQuery(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        ArrayList<Map<String, String>> formattedData = this.getFormattedData(rs);
        stmt.close();

        return formattedData;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return this.conn.prepareStatement(query);
    }

    private ArrayList<Map<String, String>> getFormattedData(ResultSet rs) 
    throws SQLException {
        ResultSetMetaData metadata = rs.getMetaData();
        Integer columnCount = metadata.getColumnCount();
        String[] columnName = new String[columnCount + 1];

        for (int i = 1; i <= columnCount; i++) {
            columnName[i] = metadata.getColumnName(i);      
        }

        ArrayList<Map<String, String>> result = new ArrayList<>();

        while(rs.next()) {
            Map<String, String> data = new HashMap<>();
            for(int i = 1; i <= columnCount; i++) {
                data.put(columnName[i], rs.getString(i));
            }

            result.add(data);
        }

        return result;
    }

    private void connect() throws SQLException {
        String url = String.format(
                        "jdbc:postgresql://%s:%s/%s",
                        this.dbHost, this.dbPort, this.dbName
                     );
        this.conn = DriverManager.getConnection(url, this.dbUsername, this.dbPassword);
    }

    private void loadConfig() throws IOException {
        Map<String, Object> configMap = this.loadYaml("src/db_config.yaml");
        this.mapConfigToVar(configMap);
    }

    private Map<String, Object> loadYaml(String filepath) throws IOException {
        File configFile = new File(filepath);
        InputStream configStream = new FileInputStream(configFile);
        Yaml yaml = new Yaml();
        return (Map<String, Object>) yaml.load(configStream);
    }

    private void mapConfigToVar(Map<String, Object> configMap) {
        this.dbHost = (String) configMap.get("POSTGRES_HOST");
        this.dbName = (String) configMap.get("POSTGRES_DB");
        this.dbUsername = (String) configMap.get("POSTGRES_USER");
        this.dbPassword = (String) configMap.get("POSTGRES_PASSWORD");
        this.dbPort = (String) configMap.get("POSTGRES_PORT");
    }
}