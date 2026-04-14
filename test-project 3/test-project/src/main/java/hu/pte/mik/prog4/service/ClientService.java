package hu.pte.mik.prog4.service;


import hu.pte.mik.prog4.model.Client;

public interface ClientService<T extends Client> {

    public void pay(T client);

    public void receiveService(T client);

}
