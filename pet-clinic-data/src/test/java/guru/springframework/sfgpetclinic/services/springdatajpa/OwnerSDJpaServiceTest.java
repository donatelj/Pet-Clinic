package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    public static final String LAST_NAME = "Smith";

    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerSDJpaService ownerSDJpaService;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastname() {

        when(ownerRepository.findByLastName(any())).thenReturn(owner);

        Owner smith = ownerSDJpaService.findByLastname(LAST_NAME);

        assertEquals(smith.getLastName(), LAST_NAME);

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerset = new HashSet<>();
        ownerset.add(Owner.builder().id(1L).build());
        ownerset.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(ownerset);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(owners.size(), 2);
    }

    @Test
    void findById() {
        Owner ownerReturn = Owner.builder().id(1L).build();

        when(ownerRepository.findById(any())).thenReturn(Optional.of(ownerReturn));

        Owner owner = ownerSDJpaService.findById(1L);

        assertNotNull(owner);
        assertEquals(owner.getId(), 1L);
    }

    @Test
    void findByIdNotFound() {

        when(ownerRepository.findById(any())).thenReturn(Optional.empty());

        Owner owner = ownerSDJpaService.findById(1L);

        assertNull(owner);

    }

    @Test
    void save() {
        Owner owner2Save = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(owner);
        Owner savedOwner = ownerRepository.save(owner2Save);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(any());
    }
}