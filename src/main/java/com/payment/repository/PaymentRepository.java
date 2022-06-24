package com.payment.repository;

import com.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByClientId(Long id);

    List<Payment> findByClientFirstName(String firstName);

    List<Payment> findByClientLastName(String lastName);

    void deleteByClientId(Long id);
}
