package com.himanshuntwk.springoauthserver.repository;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Used temoporarily for demo, not required in final code.
 */
public class ClientRepository implements RegisteredClientRepository {

  Map<String, RegisteredClient> clients = new HashMap<>();

  @Override
  public void save(RegisteredClient registeredClient) {
    clients.put(registeredClient.getClientId(), registeredClient);
  }

  @Override
  public RegisteredClient findById(String id) {
    return clients.get(id);
  }

  @Override
  public RegisteredClient findByClientId(String clientId) {
    return clients.get(clientId);
  }
}
