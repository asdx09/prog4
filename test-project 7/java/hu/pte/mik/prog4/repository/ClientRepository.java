package hu.pte.mik.prog4.repository;

import hu.pte.mik.prog4.model.Client;

import java.util.List;

public interface ClientRepository<T extends Client> {

    List<T> listAll();

    T save(T client);

    T findById(Long id);

}
