package ru.shipov.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@JacksonXmlRootElement(localName = "APPLICATION")
@JsonRootName(value = "APPLICATION")
@Table(name = "APPLICATION")
public class Application {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID", insertable = false, updatable = false)
    private Contact contact;

    @Id
    @GeneratedValue
    @Column(name = "APPLICATION_ID", nullable = false)
    @JacksonXmlProperty(localName = "APPLICATION_ID")
    @JsonProperty(value = "APPLICATION_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "DT_CREATED", nullable = false)
    @JacksonXmlProperty(localName = "DT_CREATED")
    @JsonProperty(value = "DT_CREATED")
    private Date dtCreated;

    @Column(name = "PRODUCT_NAME", length = 64, nullable = false)
    @JacksonXmlProperty(localName = "PRODUCT_NAME")
    @JsonProperty(value = "PRODUCT_NAME")
    private String productName;

    @Column(name = "CONTACT_ID", nullable = false)
    @JacksonXmlProperty(localName = "CONTACT_ID")
    @JsonProperty(value = "CONTACT_ID")
    private Long contactId;

    public Application() {
    }

    public Application(Contact contact, Date dtCreated, String productName, Long contactId) {
        this.contact = contact;
        this.dtCreated = dtCreated;
        this.productName = productName;
        this.contactId = contactId;
    }

    public Contact getContact() {
        return contact;
    }

    public Long getId() {
        return id;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public String getProductName() {
        return productName;
    }

    public Long getContactId() {
        return contactId;
    }
}
