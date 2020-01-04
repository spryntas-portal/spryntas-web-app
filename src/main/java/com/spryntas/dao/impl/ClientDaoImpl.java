package com.spryntas.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.spryntas.dao.ClientDao;
import com.spryntas.domain.Client;
import com.spryntas.exception.BadRequestException;

@Repository(value="clientDao")
public class ClientDaoImpl implements ClientDao{
	
	private static final Logger LOGGER = LogManager.getLogger(ClientDaoImpl.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public Client getClientById(Integer clientId) {
		LOGGER.info("Retrieving client details by clientID");
		String sql = "select client_id,name,email,functional_domain,url,locality from client where client_id=?;";
		Client client = jdbcTemplate.queryForObject(sql, new Object[] {clientId},
				new BeanPropertyRowMapper<Client>(Client.class));
		return client;
	}
	
	@Override
	public Client getClientByEmail(String email) {
		LOGGER.info("Retrieving client details by email"+email);
		String sql = "select client_id,name,email,functional_domain,url,locality from client where email=?;";
		Client client = jdbcTemplate.queryForObject(sql, new Object[] {email},
				new BeanPropertyRowMapper<Client>(Client.class));
		return client;
	}
	
	@Override
	public List<Client> getAllClients(){
		List<Client> clientList = new ArrayList<Client>();
		clientList = null;
		LOGGER.info("Retrieving all client list details ");
		String sql = "select client_id,name,email,functional_domain,url,locality from client;";
		clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Client>(Client.class));
		return clientList;
	}
	
	@Override
	public Client insertClient(Client client) {

		LOGGER.info("Creating client by given client info");
		String sql = "insert into client(name,email,url,locality) values(?,?,?,?);";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(sql, new String[] {"client_id"});
				statement.setString(1, client.getName());
				statement.setString(2, client.getEmail());
				statement.setString(3, client.getUrl());
				statement.setString(4, client.getLocality());
				return statement;
			}
		}, holder);
		if (holder.getKey() == null)
			throw new BadRequestException("Client not inserted");
		return getClientById(holder.getKey().intValue());
	}
	
}
