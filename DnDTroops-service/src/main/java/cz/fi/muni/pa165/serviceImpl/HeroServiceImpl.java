/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.service.HeroService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Filip Ksenzuliak
 */
@Service
@Transactional
public class HeroServiceImpl implements HeroService{

    @Autowired
    private Mapper mapper;
    
    @Autowired
    private HeroDAO heroDao;
    
    public HeroServiceImpl() { }
    
    public HeroServiceImpl(HeroDAO heroDao, Mapper mapper) {
        this.heroDao = heroDao;
        this.mapper = mapper;
    }
    
    private HeroDTO mapDTO(Hero hero) {
        return mapper == null || hero == null ? null : mapper.map(hero, HeroDTO.class);
    }
    
    private Hero mapEntity(HeroDTO heroDTO) {
        return mapper == null || heroDTO == null ? null : mapper.map(heroDTO, Hero.class);
    }
    
    @Override
    public HeroDTO createHero(HeroDTO hero) {
        return mapDTO(heroDao.createHero(mapEntity(hero)));
    }

    @Override
    public HeroDTO updateHero(HeroDTO hero) {
        return mapDTO(heroDao.updateHero(mapEntity(hero)));
    }

    @Override
    public Boolean deleteHero(HeroDTO hero) {
        return heroDao.deleteHero(mapEntity(hero));
    }

    @Override
    public List<HeroDTO> retrieveAllHeroes() {
        List<HeroDTO> allHeroes = new ArrayList();
        for(Hero h : heroDao.retrieveAllHeroes()) {
            allHeroes.add(mapDTO(h));
        }
        return allHeroes;
    }

    @Override
    public HeroDTO retrieveHeroById(long id) {
        return mapDTO(heroDao.retrieveHeroById(id));
    }

    @Override
    public HeroDTO retrieveHeroByName(String name) {
        return mapDTO(heroDao.retrieveHeroByName(name));
    }

    @Override
    public Boolean deleteAllHeroes() {
        Boolean res = true;
        for(HeroDTO h : retrieveAllHeroes()) {
            res &= deleteHero(h);
        }
        return res;
    }
    
}