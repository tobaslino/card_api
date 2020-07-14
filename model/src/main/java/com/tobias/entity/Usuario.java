package com.tobias.entity;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

import javax.json.Json;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Usuario extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // Personal information.
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String cnpj;
    @Column
    private String phone;
    @Column
    private String email;
    // Account and Transaction information.
    @Column
    private BigDecimal actualBalance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historic")
    private Historic historic;

    public static Usuario build(String name) {
        var n = new Usuario();
        n.userName = name;
        return n;
    }

    public String getName() {
        return userName;
    }

    public void setIdentity(String cnpj) {
        Client client = ClientBuilder.newClient();
        Response response = client
                            .target("https://www.receitaws.com.br/v1/cnpj/" + cnpj)
                            .request()
                            .get();
        String jsonObject = response.readEntity(String.class);
        this.cnpj = Json.createReader(new ByteArrayInputStream(jsonObject.getBytes())).readObject().getString("status");
    }

}