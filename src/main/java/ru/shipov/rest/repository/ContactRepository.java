package ru.shipov.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shipov.rest.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    public Optional<Contact> findById(Long contactId);

    public List<Contact> findAll();

//    @Query("SELECT app FROM Application app, Contact con WHERE con.contactId = ?1 AND app.dtCreated = (SELECT MAX(app.dtCreated) FROM app, con WHERE con.contactId = ?1)")
//    public Application findApplication(Long contactId);
}
