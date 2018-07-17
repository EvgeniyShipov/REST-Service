package ru.shipov.rest.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CONTACT")
public class Contact {

    @Id
    @GeneratedValue
    @Column(name = "CONTACT_ID", nullable = false)
    private Long contactId;

    @OneToMany(mappedBy = "contact")
    @Column(name = "APPLICATION_ID", nullable = false)
    private Set<Application> applications = new HashSet<>();

    public Contact() {
    }

    public Long getContactId() {
        return contactId;
    }

    public Set<Application> getApplications() {
        return applications;
    }
}
