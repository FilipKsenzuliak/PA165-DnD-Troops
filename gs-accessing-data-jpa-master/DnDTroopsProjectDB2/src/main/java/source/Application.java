package source;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Application {
    
    public static void main(String[] args) {
    	
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        MissionRepository mRepository = context.getBean(MissionRepository.class);
        // save a couple of customers
        /**repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
        **/
        mRepository.save(new Mission("Alpha","SUV",900));

        // fetch all customers
        /**
         * Iterable<Customer> customers = repository.findAll();
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println();
        * **/
        
        Iterable<Mission> missions = mRepository.findAll();
        System.out.println("Missions found with findAll():");
        System.out.println("-------------------------------");
        for (Mission mission : missions) {
            System.out.println(mission);
        }

        
/**
// fetch an individual customer by ID
        Customer customer = repository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // fetch customers by last name
        List<Customer> bauers = repository.findByLastName("Bauer");
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        for (Customer bauer : bauers) {
            System.out.println(bauer);
        }
        * */

        context.close();
    }

}
