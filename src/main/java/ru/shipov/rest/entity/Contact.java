package ru.shipov.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@JacksonXmlRootElement(localName = "CONTACT")
@JsonRootName(value = "CONTACT")
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue
    @Column(name = "CONTACT_ID", nullable = false)
    @JacksonXmlProperty(localName = "CONTACT_ID")
    @JsonProperty(value = "CONTACT_ID")
    @ApiModelProperty("contact id")
    private Long id;

    public Contact() {
    }

    public Long getId() {
        return id;
    }
}
