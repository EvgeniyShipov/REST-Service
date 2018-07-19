package ru.shipov.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shipov.rest.entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {

    public Optional<Application> findById(Integer contactId);

    public List<Application> findAll();

    public Application findFirstByContactIdOrderByDtCreatedDesc(Integer contactId);
}
