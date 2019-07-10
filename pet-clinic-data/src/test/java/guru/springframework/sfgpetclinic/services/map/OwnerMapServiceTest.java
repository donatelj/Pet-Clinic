package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerMapServiceTest {
    private final Long ownerId = 1L;
    private final String lastName = "Smith";
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(owners.size(), 1);
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(ownerMapService.findAll().size(), 0);
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(ownerMapService.findAll().size(), 0);
    }

    @Test
    void saveExistingId() {
        Owner owner2 = ownerMapService.save(Owner.builder().id(2L).build());

        assertEquals(owner2.getId(), 2L);
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(owner.getId(), ownerId);
    }

    @Test
    void findByLastname() {
        Owner smith = ownerMapService.findByLastname(lastName);

        assertNotNull(smith);
        assertEquals(smith.getLastName(), lastName);
    }

    @Test
    void findByLastnameNotFound() {
        Owner smith = ownerMapService.findByLastname("foo");

        assertNull(smith);
    }
}