package com.payment.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Client extends BaseEntity {

    @OneToMany(mappedBy = "client")
    private List<Payment> payments;
    private double amount;
}
