
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import source.Application;
import source.Troop;
import source.Hero;
import source.TroopRepository;

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
public class TroopTest {
    
    ConfigurableApplicationContext context = SpringApplication.run(Application.class);
    TroopRepository repository = context.getBean(TroopRepository.class);
    
    @Test
    public void createTest() {
        int count = 0;
        repository.save(new Troop("Fiskusovia", new Hero()));
        repository.save(new Troop("Lolobriadkovia", new Hero()));
        Iterable<Troop> troops = repository.findAll();
        for (Troop troop : troops) {
            count++;
        }
        context.close();
        Assert.assertEquals(2, count);
    }
    @Test
    public void nameLookupTest(){
        Troop troop = new Troop("Fiskusovia", new Hero());
        repository.save(troop);
        Assert.assertEquals(repository.findByName("Fiskusovia").contains(troop), true);
    }
    @Test
    public void deleteTest(){
        Troop troop = new Troop("Fiskusovia", new Hero());
        repository.save(troop);
        repository.delete(troop);
        Assert.assertEquals(repository.findByName("Fiskusovia").contains(troop), false);
    }
}
