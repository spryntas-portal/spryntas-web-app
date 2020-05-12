package com.spryntas.service.client;

import java.util.List;

import com.spryntas.model.Client;

public interface ClientDao {

	Client getClientById(Integer clientId);

	Client getClientByEmail(String email);

	List<Client> getAllClients();

	Client insertClient(Client client);

}
