package bstorm.akimts.exohotel.repositories.impl;

import bstorm.akimts.exohotel.models.entities.Client;

import javax.ejb.Singleton;

@Singleton
public class ClientRepositoryImpl extends AbstractRepository<Client, Long> {
    public ClientRepositoryImpl() {
        super(Client.class);
    }
}
