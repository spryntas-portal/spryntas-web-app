package com.spryntas.dao;

import java.util.List;

import com.spryntas.domain.Client;

public interface ClientDao {

	Client getClientById(Integer clientId);

	Client getClientByEmail(String email);

	List<Client> getAllClients();

	Client insertClient(Client client);

}
