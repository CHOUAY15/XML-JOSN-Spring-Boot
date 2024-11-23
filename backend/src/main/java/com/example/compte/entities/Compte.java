package com.example.compte.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "item")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(localName = "id")
    private Long id;

    @JacksonXmlProperty(localName = "solde")
    private double solde;

    @Temporal(TemporalType.DATE)
    @JacksonXmlProperty(localName = "dateCreation")
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    @JacksonXmlProperty(localName = "type")
    private TypeCompte type;
}