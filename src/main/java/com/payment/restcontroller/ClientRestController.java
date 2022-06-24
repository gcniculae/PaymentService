package com.payment.restcontroller;

import com.payment.converter.ClientConverter;
import com.payment.dto.ClientDto;
import com.payment.entity.Client;
import com.payment.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/clients")
@CrossOrigin(origins = "*")
public class ClientRestController {

    private final ClientService clientService;
    private final ClientConverter clientConverter;

    @Autowired
    public ClientRestController(ClientService clientService, ClientConverter clientConverter) {
        this.clientService = clientService;
        this.clientConverter = clientConverter;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAllClients(@RequestParam(name = "all", required = false, defaultValue = "false") Boolean all,
                                                          @RequestParam(name = "ids", required = false) List<Long> ids,
                                                          @RequestParam(name = "firstName", required = false) String firstName,
                                                          @RequestParam(name = "lastName", required = false) String lastName) {
        List<Client> clients = new ArrayList<>();

        if (all) {
            clients = clientService.findAllClients();
        } else if (!ids.isEmpty()) {
            clients = clientService.findClientsById(ids);
        } else if (firstName != null) {
            clients = clientService.findClientsByFirstName(firstName);
        } else if (lastName != null) {
            clients = clientService.findClientsByLastName(lastName);
        }

        return ResponseEntity.ok(clientConverter.convertEntityListToDtoList(clients));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);

        return ResponseEntity.ok(clientConverter.convertEntityToDto(client));
    }

    @GetMapping(path = "/phone/{phoneNumber}")
    public ResponseEntity<ClientDto> findClientByPhoneNumber(@PathVariable String phoneNumber) {
        Client client = clientService.findClientByPhoneNumber(phoneNumber);

        return ResponseEntity.ok(clientConverter.convertEntityToDto(client));
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@Valid @RequestBody ClientDto clientDto) {
        Client client = clientConverter.convertDtoToEntity(clientDto);
        Client savedClient = clientService.saveClient(client);

        return ResponseEntity.ok(clientConverter.convertEntityToDto(savedClient));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Long id, @Valid @RequestBody ClientDto clientDto) {
        Client client = clientConverter.convertDtoToEntity(clientDto);
        Client updatedClient = clientService.updateClient(id, client);

        return ResponseEntity.ok(clientConverter.convertEntityToDto(updatedClient));
    }

    @DeleteMapping
    public ResponseEntity<ClientDto> deleteClient(@RequestParam(name = "id", required = false) Long id,
                                                  @RequestParam(name = "phoneNumber", required = false) String phoneNumber) {
        if (id != null) {
            clientService.deleteClientById(id);
        } else if (phoneNumber != null) {
            clientService.deleteClientByPhoneNumber(phoneNumber);
        }

        return ResponseEntity.noContent().build();
    }
}
