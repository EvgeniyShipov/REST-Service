package ru.shipov.rest.dao;

import org.springframework.data.repository.CrudRepository;
import ru.shipov.rest.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactDAO extends CrudRepository<Contact, Long> {

    public Optional<Contact> findById(Long contactId);

    public List<Contact> findAll();
}
