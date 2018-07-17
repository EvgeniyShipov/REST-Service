package ru.shipov.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="APPLICATION")
public class Application {

    @JsonIgnore
    @ManyToOne
    private Contact contact;

    @Id
    @GeneratedValue
    @Column(name = "APPLICATION_ID", nullable = false)
    private Long applicationId;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_CREATED", nullable = false)
    private Date dtCreated;

    @Column(name = "PRODUCT_NAME", length = 64, nullable = false)
    private String productName;

    public Application(Contact contact, Date dtCreated, String productName) {
        this.contact = contact;
        this.dtCreated = dtCreated;
        this.productName = productName;
    }

    public Contact getContact() {
        return contact;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public String getProductName() {
        return productName;
    }
}
