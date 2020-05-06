package com.spryntas.api.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spryntas.exception.BadRequestException;
import com.spryntas.model.Client;
import com.spryntas.util.helper.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "clientService")
@Slf4j
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao clientDao;
	
	@Autowired
	EmailService emailService;

	@Override
	public Client getClientInfo(Integer clientId, String email) {
		log.info("starting getClientInfo method from clientService");
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
		log.info("starting getAllClients Info method from clientService");
		return clientDao.getAllClients();
	}

	@Override
	public Client createClient(Client client) {

		log.info("starting createClient method from clientService");
		
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
