package com.spryntas.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spryntas.dao.ClientDao;
import com.spryntas.domain.Client;
import com.spryntas.exception.BadRequestException;
import com.spryntas.service.ClientService;
import com.spryntas.service.EmailService;

@Service(value = "clientService")
public class ClientServiceImpl implements ClientService {

	private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

	@Autowired
	ClientDao clientDao;
	
	@Autowired
	EmailService emailService;

	@Override
	public Client getClientInfo(Integer clientId, String email) {
		LOGGER.info("starting getClientInfo method from clientService");
		Client client = null;

		if (clientId != null && email != null)
			throw new BadRequestException("Failed to pass clientId or email to retrieve Client Info");

		if (clientId != null)
			client = clientDao.getClientById(clientId);
		else if (email != null)
			client = clientDao.getClientByEmail(email);

		return client;
	}

	@Override
	public List<Client> getAllClients() {
		LOGGER.info("starting getAllClients Info method from clientService");
		return clientDao.getAllClients();
	}

	@Override
	public Client createClient(Client client) {

		LOGGER.info("starting createClient method from clientService");
		
		Client newClient = null;

		if (client.getName() == null)
			throw new BadRequestException("ClientName is empty");
		if (client.getEmail() == null)
			throw new BadRequestException("Client Email is empty");
		if (client.getUrl() == null)
			throw new BadRequestException("Client Url is empty");
		if (client.getLocality() == null)
			throw new BadRequestException("Client Locality is empty");
		
		newClient = clientDao.insertClient(client);
		emailService.sendSimpleMessage(newClient.getEmail(), "Welcome "+newClient.getName(), "Thanks for Choosing Spryntas Service");
		return newClient;

	}

}
