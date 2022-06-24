package com.payment.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Payment extends BaseEntity {

    @ManyToOne
    private Client client;
}
