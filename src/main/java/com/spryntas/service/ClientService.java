package com.spryntas.service;

import java.util.List;

import com.spryntas.domain.Client;

public interface ClientService {

	Client getClientInfo(Integer clientId, String email);

	List<Client> getAllClients();

	Client createClient(Client client);

}
