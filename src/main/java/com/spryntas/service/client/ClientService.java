package com.spryntas.service.client;

import java.util.List;

import com.spryntas.model.Client;

public interface ClientService {

	Client getClientInfo(Integer clientId, String email);

	List<Client> getAllClients();

	Client createClient(Client client);

}
