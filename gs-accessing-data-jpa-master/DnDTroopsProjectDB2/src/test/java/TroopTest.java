
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import source.Application;
import source.Troop;
import source.Hero;
import source.TroopRepository;
import source.Race;

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
        repository.save(new Troop("Fiskusovia", new Hero("Jozo", Race.ELF, 20,2)));
        repository.save(new Troop("Lolobriadkovia", new Hero("Ivo", Race.DWARF, 50,1)));
        Iterable<Troop> troops = repository.findAll();
        for (Troop troop : troops) {
            count++;
        }
        context.close();
        Assert.assertEquals(2, count);
    }
    @Test
    public void nameLookupTest(){
        Troop troop1 = new Troop("Fiskusovia", new Hero("Jana", Race.HUMAN, 22,2));
        repository.save(troop1);
        Assert.assertEquals(repository.findByName("Fiskusovia").contains(troop1), true);
    }
    @Test
    public void deleteTest(){
        Troop troop = new Troop("Fiskusovia", new Hero("Lolo", Race.GNOME, 15,1));
        repository.save(troop);
        repository.delete(troop);
        Assert.assertEquals(repository.findByName("Fiskusovia").contains(troop), false);
    }
}
