package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType savedDogPetType = petTypeService.Save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.Save(cat);

        Owner owner1 = new Owner();
        //owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("442 Du Blizzard");
        owner1.setCity("St-Jean");
        owner1.setTelephone("450-515-5555");

        Pet dog1 = new Pet();
        dog1.setPetType(savedDogPetType);
        dog1.setName("Lacie");
        dog1.setBirthDate(LocalDate.of(2008, 12, 27));
        dog1.setOwner(owner1);
        owner1.getPets().add(dog1);

        Pet dog2 = new Pet();
        dog2.setPetType(savedDogPetType);
        dog2.setName("Daisie");
        dog2.setBirthDate(LocalDate.of(2008, 12, 27));
        dog2.setOwner(owner1);
        owner1.getPets().add(dog2);

        ownerService.Save(owner1);

        Owner owner2 = new Owner();
        //owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("446 Du Blizzard");
        owner2.setCity("St-Jean");
        owner2.setTelephone("450-515-5566");

        Pet cat1 = new Pet();
        cat1.setPetType(savedCatPetType);
        cat1.setName("Cocotte");
        cat1.setBirthDate(LocalDate.of(2015, 8, 2));
        cat1.setOwner(owner2);
        owner2.getPets().add(cat1);

        ownerService.Save(owner2);

        System.out.println("loaded Owners...");

        Vet vet1 = new Vet();
        //vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        Specialty specialty1 = new Specialty();
        specialty1.setDescription("Radiology");
        vet1.getSpecialities().add(specialtyService.Save(specialty1));

        Specialty specialty2 = new Specialty();
        specialty2.setDescription("Surgery");
        vet1.getSpecialities().add(specialtyService.Save(specialty2));

        vetService.Save(vet1);

        Vet vet2 = new Vet();
        //vet2.setId(2L);
        vet2.setFirstName("John");
        vet2.setLastName("Michael");

        Specialty specialty3 = new Specialty();
        specialty3.setDescription("Dentistry");
        vet2.getSpecialities().add(specialtyService.Save(specialty3));

        vetService.Save(vet2);

        System.out.println("loaded Vets...");

    }
}
