
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import source.Application;
import source.Customer;
import source.CustomerRepository;
import source.Mission;
import source.MissionRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
public class MissionTest {
    @Test
    public void AppTest() {
        int count = 0;
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        MissionRepository repository = context.getBean(MissionRepository.class);
        repository.save(new Mission("Killing", "Kill them all", 20000));
        repository.save(new Mission("Feeding", "Feed the poor", 500));
        Iterable<Mission> missions = repository.findAll();
        for (Mission mission : missions) {
            count++;
        }
        context.close();
        Assert.assertEquals(2, count);
    }
    
}
