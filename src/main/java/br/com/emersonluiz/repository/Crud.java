package br.com.emersonluiz.repository;

import java.util.List;

public interface Crud<T> {

    T create(T t) throws Exception;

    T findOne(int id) throws Exception;

    void update(int id, T t) throws Exception;

    void delete(int id) throws Exception;

    List<T> findAll() throws Exception;
}
