package ru.shipov.rest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "APPLICATION_ID", nullable = false)
    @JacksonXmlProperty(localName = "APPLICATION_ID")
    @JsonProperty(value = "APPLICATION_ID")
    @ApiModelProperty("application id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "DT_CREATED", nullable = false)
    @JacksonXmlProperty(localName = "DT_CREATED")
    @JsonProperty(value = "DT_CREATED")
    @ApiModelProperty("date of application created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date dtCreated;

    @Column(name = "PRODUCT_NAME", length = 64, nullable = false)
    @JacksonXmlProperty(localName = "PRODUCT_NAME")
    @JsonProperty(value = "PRODUCT_NAME")
    @ApiModelProperty("product name")
    private String productName;

    @Column(name = "CONTACT_ID", nullable = false)
    @JacksonXmlProperty(localName = "CONTACT_ID")
    @JsonProperty(value = "CONTACT_ID")
    @ApiModelProperty("contact id")
    private Integer contactId;

    public Application() {
    }

    public Application(Contact contact, Date dtCreated, String productName, Integer contactId) {
        this.contact = contact;
        this.dtCreated = dtCreated;
        this.productName = productName;
        this.contactId = contactId;
    }

    public Application(Integer id, Contact contact, Date dtCreated, String productName, Integer contactId) {
        this.id = id;
        this.contact = contact;
        this.dtCreated = dtCreated;
        this.productName = productName;
        this.contactId = contactId;
    }

    public Contact getContact() {
        return contact;
    }

    public Integer getId() {
        return id;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getContactId() {
        return contactId;
    }
}
