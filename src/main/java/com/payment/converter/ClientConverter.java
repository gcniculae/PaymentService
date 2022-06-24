package com.payment.converter;

import com.payment.dto.ClientDto;
import com.payment.entity.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    @Override
    public ClientDto convertEntityToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);

        return clientDto;
    }

    @Override
    public Client convertDtoToEntity(ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);

        return client;
    }
}
