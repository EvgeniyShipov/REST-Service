package ru.shipov.rest.dao;

import org.springframework.data.repository.CrudRepository;
import ru.shipov.rest.entity.Application;

import java.util.List;

public interface ApplicationDAO extends CrudRepository<Application, Long> {

    public List<Application> findAll();
}
