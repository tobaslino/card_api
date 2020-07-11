package com.tobias.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Address extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String cep;
    @Column
    private String logradouro;
    @Column
    private String complemento;
    @Column
    private String bairro;
    @Column
    private String localidade;
    @Column
    private String uf;
    @Column
    private String unidade;
    @Column
    private long ibge;
    @Column
    private long gia;
}