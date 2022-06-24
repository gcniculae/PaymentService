package com.payment.restcontroller;

import com.payment.converter.ClientConverter;
import com.payment.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestController {

    private final ClientService clientService;
    private final ClientConverter clientConverter;

    @Autowired
    public ClientRestController(ClientService clientService, ClientConverter clientConverter) {
        this.clientService = clientService;
        this.clientConverter = clientConverter;
    }
}
