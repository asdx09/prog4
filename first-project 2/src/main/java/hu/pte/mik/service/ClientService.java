package hu.pte.mik.service;

import hu.pte.mik.model.Client;

public interface ClientService<T extends Client> {

    public void pay(T client);

    public void receiveService(T client);

}
