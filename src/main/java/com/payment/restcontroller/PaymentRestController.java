package com.payment.restcontroller;

import com.payment.converter.PaymentConverter;
import com.payment.dto.PaymentDto;
import com.payment.entity.Payment;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/payments")
@CrossOrigin(origins = "*")
public class PaymentRestController {

    private final PaymentService paymentService;
    private final PaymentConverter paymentConverter;

    @Autowired
    public PaymentRestController(PaymentService paymentService, PaymentConverter paymentConverter) {
        this.paymentService = paymentService;
        this.paymentConverter = paymentConverter;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> findPayments(@RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                         @RequestParam(name = "ids", required = false) List<Long> ids,
                                                         @RequestParam(name = "firstName", required = false) String firstName,
                                                         @RequestParam(name = "lastName", required = false) String lastName,
                                                         @RequestParam(name = "clientId", required = false) Long clientId) {
        List<Payment> payments = new ArrayList<>();

        if (all) {
            payments = paymentService.findAllPayments();
        } else if (ids != null) {
            payments = paymentService.findPaymentsById(ids);
        } else if (firstName != null) {
            payments = paymentService.findPaymentsByClientFirstName(firstName);
        } else if (lastName != null) {
            payments = paymentService.findPaymentsByClientLastName(lastName);
        } else if (clientId != null) {
            payments = paymentService.findPaymentsByClientId(clientId);
        }

        return ResponseEntity.ok(paymentConverter.convertEntityListToDtoList(payments));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PaymentDto> findPayment(@PathVariable("id") Long id) {
        Payment payment = paymentService.findPaymentById(id);

        return ResponseEntity.ok(paymentConverter.convertEntityToDto(payment));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> addClient(@Valid @RequestBody PaymentDto paymentDto) {
        Payment payment = paymentConverter.convertDtoToEntity(paymentDto);
        Payment savedPayment = paymentService.savePayment(payment, paymentDto.getClientId());

        return ResponseEntity.ok(paymentConverter.convertEntityToDto(savedPayment));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable("id") Long id, @Valid @RequestBody PaymentDto paymentDto) {
        Payment payment = paymentConverter.convertDtoToEntity(paymentDto);
        Payment updatedPayment = paymentService.updatePayment(id, payment, paymentDto.getClientId());

        return ResponseEntity.ok(paymentConverter.convertEntityToDto(updatedPayment));
    }

    @DeleteMapping
    public ResponseEntity<PaymentDto> deletePayment(@RequestParam(name = "id", required = false) Long id,
                                                    @RequestParam(name = "clientId", required = false) Long clientId) {
        if (id != null) {
            paymentService.deletePaymentById(id);
        } else if (clientId != null) {
            paymentService.deletePaymentsByClientId(clientId);
        }

        return ResponseEntity.noContent().build();
    }
}
