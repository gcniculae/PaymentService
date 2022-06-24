package com.payment.service;

import com.payment.entity.Client;
import com.payment.exception.NotFoundException;
import com.payment.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isPresent()) {
            return clientOptional.get();
        } else {
            throw new NotFoundException("No such client found.", "client.not.found");
        }
    }

    public List<Client> findClientsByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    public List<Client> findClientsByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    public Client findClientByPhoneNumber(String phoneNumber) {
        Optional<Client> optionalClient = clientRepository.findByPhoneNumber(phoneNumber);

        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new NotFoundException("No such client found.", "client.not.found");
        }
    }

    public Client updateClient(Long id, Client client) {
        Client clientById = findClientById(id);
        client.setId(clientById.getId());

        return saveClient(client);
    }

    public void deleteClientById(Long id) {
        Client clientById = findClientById(id);

        clientRepository.deleteById(clientById.getId());
    }

    @Transactional
    public void deleteClientByPhoneNumber(String phoneNumber) {
        Client clientByPhoneNumber = findClientByPhoneNumber(phoneNumber);

        clientRepository.deleteByPhoneNumber(clientByPhoneNumber.getPhoneNumber());
    }
}
