package hu.mik.pte.prog5.zh1vaadin.service;

import hu.mik.pte.prog5.zh1vaadin.entity.Pet;
import hu.mik.pte.prog5.zh1vaadin.entity.PetSpecies;
import hu.mik.pte.prog5.zh1vaadin.repository.PetRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("petService")
public class PetServiceImp implements PetService{

    private final PetRepository petRepository;

    public PetServiceImp(@Qualifier("mock")PetRepository petRepository)
    {
        this.petRepository = petRepository;
    }

    @Override
    @PreAuthorize("hasRole('REGISTER')")
    public Pet save(Pet pet) {
        return this.petRepository.save(pet);
    }

    @Override
    @PreAuthorize("hasRole('REGISTER')")
    public List<PetSpecies> listSpecies() {
        return List.of(PetSpecies.values());
    }

    @Override
    @PostFilter("@securityService.getRolesAsString().contains(filterObject.species.name())")
    public List<Pet> listAll()
    {
        return this.petRepository.findAll();
    }
}
