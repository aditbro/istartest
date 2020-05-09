package com.aditya.istartest.models;

public interface BaseService<M extends Model> {
    public Model getById(Integer id) throws Exception;
    public void update(M m) throws Exception;
    public void add(M m) throws Exception;
    public void delete(Integer id) throws Exception;
}