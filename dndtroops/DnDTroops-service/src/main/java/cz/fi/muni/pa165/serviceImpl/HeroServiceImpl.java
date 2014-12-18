/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.api.dto.HeroDTO;
import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.api.service.HeroService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Filip Ksenzuliak
 */
@Service
@Transactional
public class HeroServiceImpl implements HeroService{

    private HeroDAO heroDAO;
    private Mapper mapper = new DozerBeanMapper();
    
    public HeroDAO getHeroDAO() {
        return heroDAO;
    }
    
    public void setHeroDAO(HeroDAO heroDAO) {
        this.heroDAO = heroDAO;
    }
       
    @Override
    @Transactional
    public void createHero(HeroDTO hero) {
        Validate.notNull(hero, "Argument is null.");
        
        heroDAO.createHero(mapper.map(hero, Hero.class));
    }

    @Override
    @Transactional
    public void updateHero(HeroDTO hero) {
        Validate.notNull(hero, "Argument is null.");
        
        heroDAO.updateHero(mapper.map(hero, Hero.class));
    }
    
    @Override
    @Transactional
    public void updateHeroes(List<HeroDTO> heroes) {
        Validate.notNull(heroes, "Argument is null.");
        
        for(HeroDTO hero : heroes) {
            heroDAO.updateHero(mapper.map(hero, Hero.class));
        }
    }

    @Override
    @Transactional
    public void deleteHero(HeroDTO hero) {
        Validate.notNull(hero, "Argument is null.");
        
        heroDAO.deleteHero(mapper.map(hero, Hero.class));
    }

    @Override
    @Transactional
    public void deleteAllHeroes() {
        List<Hero> heroes = heroDAO.getAllHeroes();
        for(Hero h : heroes) {
            heroDAO.deleteHero(h);
        }
    }

    @Override
    @Transactional
    public List<HeroDTO> getAllHeroes() {
        List<HeroDTO> heroesDTO = new ArrayList<HeroDTO>();
        for(Hero hero : heroDAO.getAllHeroes()) {
            heroesDTO.add(mapper.map(hero, HeroDTO.class));
        }
        return heroesDTO;
    }

    @Override
    @Transactional
    public HeroDTO getHeroById(Long id) {
        Validate.isTrue(id > 0, "Invalid id!");
        Validate.isTrue(id != null, "Id is null.");
        
        HeroDTO hero = mapper.map(heroDAO.getHeroById(id), HeroDTO.class);
                System.out.println(hero);
        return hero;
    }

    @Override
    @Transactional
    public List<HeroDTO> findHeroByName(String name) {
        Validate.isTrue(!name.isEmpty(), "Empty name!");
        
        List<HeroDTO> heroes = new ArrayList<HeroDTO>();   
        try{
            for(Hero hero : heroDAO.findHeroByName(name)) {
                heroes.add(mapper.map(hero, HeroDTO.class));
            }
        }catch(Exception e){
            throw new DataAccessException("persistance error") {};
        }
        return heroes;
    }
    
}
