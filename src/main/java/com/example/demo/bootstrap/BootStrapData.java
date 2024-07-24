package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;
    private final InhousePartRepository inhousePartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository, InhousePartRepository inhousePartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
        this.inhousePartRepository = inhousePartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */

        InhousePart guitarBody = new InhousePart();
        guitarBody.setName("Guitar Body");
        guitarBody.setId(001);
        guitarBody.setInv(49);
        guitarBody.setPrice(124.99);
        guitarBody.setMinPartInv(1);
        guitarBody.setMaxPartInv(50);

        InhousePart guitarNeck = new InhousePart();
        guitarNeck.setName("Guitar Neck");
        guitarNeck.setId(002);
        guitarNeck.setInv(32);
        guitarNeck.setPrice(74.99);
        guitarNeck.setMinPartInv(1);
        guitarNeck.setMaxPartInv(50);

        OutsourcedPart pickups = new OutsourcedPart();
        pickups.setCompanyName("Seymour Duncan");
        pickups.setName("Pickup");
        pickups.setInv(21);
        pickups.setPrice(99.00);
        pickups.setId(003);
        pickups.setMaxPartInv(1);
        pickups.setMinPartInv(25);

        OutsourcedPart bridge = new OutsourcedPart();
        bridge.setCompanyName("Fender");
        bridge.setName("Bridge");
        bridge.setInv(14);
        bridge.setPrice(79.00);
        bridge.setId(004);
        bridge.setMaxPartInv(1);
        bridge.setMinPartInv(25);

        OutsourcedPart knobs = new OutsourcedPart();
        knobs.setCompanyName("Gibson");
        knobs.setName("Knobs");
        knobs.setInv(192);
        knobs.setPrice(19.00);
        knobs.setId(005);
        knobs.setMaxPartInv(1);
        knobs.setMinPartInv(250);

        if (inhousePartRepository.count() == 0) {
            inhousePartRepository.save(guitarBody);
            inhousePartRepository.save(guitarNeck);
            outsourcedPartRepository.save(pickups);
            outsourcedPartRepository.save(bridge);
            outsourcedPartRepository.save(knobs);

        }

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        Product acousticGuitar = new Product("Acoustic Guitar",899.00,15);
        Product electricGuitar = new Product("Electric Guitar",499.00,15);
        Product bassGuitar = new Product("Bass Guitar",399.00,10);
        Product steelGuitar = new Product("Steel Guitar",699.00,5);
        Product spanishGuitar = new Product("Spanish/Classical Guitar",799.00,5);

        if (productRepository.count() == 0) {
            productRepository.save(acousticGuitar);
            productRepository.save(electricGuitar);
            productRepository.save(bassGuitar);
            productRepository.save(steelGuitar);
            productRepository.save(spanishGuitar);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
