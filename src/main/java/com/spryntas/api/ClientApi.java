package com.spryntas.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spryntas.domain.Client;
import com.spryntas.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/client")
@Api(value = "clients", description = "client CRUD operations")
public class ClientApi {

	private static final Logger LOGGER = LogManager.getLogger(ClientApi.class);

	@Autowired
	ClientService clientService;

	@GetMapping("/{clientId}")
	@ApiOperation(value = "Get client info by client id", response = Client.class)
	public Client getClientInfo(@PathVariable("clientId") Integer id) {
		LOGGER.info("Started getClientInfo by Id method from ClientAPI");
		return clientService.getClientInfo(id, null);
	}

	@GetMapping()
	@ApiOperation(value = "Get All client info/ByEmail", response = Client.class, responseContainer = "List")
	public List<Client> getAllClientInfo(@RequestParam(value = "email", required = false) String email) {
		LOGGER.info("Started getAllClientInfo/Email method from ClientAPI");
		List<Client> clientList = new ArrayList<Client>();
		Client client = null;
		if (email != null) {
			client = clientService.getClientInfo(null, email);
			clientList.add(client);
		}else
			clientList = clientService.getAllClients();
		return clientList;
	}

	@PostMapping()
	@ApiOperation(value = "Insert Client details in Client table", response = Client.class)
	public Client saveClientInfo(@RequestBody Client client) {
		LOGGER.info("Started saveClientInfo method from ClientAPI");
		return clientService.createClient(client);
	}

}
