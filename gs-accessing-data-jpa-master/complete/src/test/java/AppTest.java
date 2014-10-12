/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import source.Application;
import source.Customer;
import source.CustomerRepository;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author gh
 */
public class AppTest {
    @Test
    public void AppTest() {
        int count = 0;
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CustomerRepository repository = context.getBean(CustomerRepository.class);
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        Iterable<Customer> customers = repository.findAll();
        for (Customer customer : customers) {
            count++;
        }
        context.close();
        Assert.assertEquals(2, count);
    }
    
}
