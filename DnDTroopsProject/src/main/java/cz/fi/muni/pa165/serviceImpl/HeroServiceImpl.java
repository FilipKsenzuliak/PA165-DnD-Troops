/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.service.HeroService;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Filip Ksenzuliak
 */
public class HeroServiceImpl implements HeroService{

    @Autowired
    private HeroDAO heroDAO;
    
    private Mapper mapper = new DozerBeanMapper();
    
    public HeroDAO getHeroDAO() {
        return heroDAO;
    }
    
    public void setHeroDAO(HeroDAO heroDAO) {
        this.heroDAO = heroDAO;
    }
       
    @Override
    public void createHero(HeroDTO hero) {
        Validate.notNull(hero, "Argument is null.");
        
        Hero createdHero = mapper.map(hero, Hero.class);
        heroDAO.createHero(createdHero);
        hero.setId(hero.getId());
    }

    @Override
    public void updateHero(HeroDTO hero) {
        Validate.notNull(hero, "Argument is null.");
        Validate.isTrue(hero.getId() > 0, "Hero is not present in DB.");
        
        heroDAO.updateHero(mapper.map(hero, Hero.class));
    }

    @Override
    public void deleteHero(HeroDTO hero) {
        Validate.notNull(hero, "Argument is null.");
        Validate.isTrue(hero.getId() > 0, "Hero is not present in DB.");
        
        heroDAO.deleteHero(mapper.map(hero, Hero.class));
    }

    @Override
    public void deleteAllHeroes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateHeroes(List<HeroDTO> heroes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<HeroDTO> getAllHeroes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HeroDTO getHeroById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<HeroDTO> getHeroByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
