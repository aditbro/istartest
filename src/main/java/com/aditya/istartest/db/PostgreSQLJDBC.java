package com.aditya.istartest.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Map;

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

    public Integer executeUpdate(String query) throws SQLException {
        Statement stmt = this.conn.createStatement();
        Integer affectedRows = stmt.executeUpdate(query);
        stmt.close();

        return affectedRows;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = this.conn.createStatement();
        ResultSet data = stmt.executeQuery(query);
        stmt.close();

        return data;
    }

    private void connect() throws SQLException {
        String url = String.format(
                        "jdbc:postgresql://%s:%s/%s",
                        this.dbHost, this.dbPort, this.dbName
                     );

        this.conn = DriverManager.getConnection(url, this.dbUsername, this.dbPassword);
    }

    private void loadConfig() throws IOException {
        Map<String, Object> configMap = this.loadYaml("./config.yaml");
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
        this.dbUsername = (String) configMap.get("POSTGRES_USERNAME");
        this.dbPassword = (String) configMap.get("POSTGERS_PASSWORD");
        this.dbPort = (String) configMap.get("POSTGERS_PORT");
    }
}