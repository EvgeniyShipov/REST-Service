package ru.shipov.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shipov.rest.entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

    public Optional<Application> findById(Long contactId);

    public List<Application> findAll();

    public Application findFirstByContactIdOrderByDtCreatedDesc(Long contactId);
}
