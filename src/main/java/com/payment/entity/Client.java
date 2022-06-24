package com.payment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client extends BaseEntity {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "client")
    private List<Payment> payments;
}
