/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dto.HeroDTO;
import java.util.List;

/**
 *
 * @author Filip Ksenzuliak
 */
public interface HeroService {
    
    /**
     * Creates new hero in database and return him.
     * 
     * @param hero  Hero, what we want save to database without id
     * @return  hero with id from database
     */
    public HeroDTO createHero (HeroDTO hero);
    
    /**
     * Update hero in database and return updated hero.
     * @param hero  hero need to update
     * @return  updated hero
     */
    public HeroDTO updateHero (HeroDTO hero);

    /**
     * Delete hero from database.
     * @param hero hero, what we want delete from database
     * @return  true if hero is deleted, false otherwise
     */
    public Boolean deleteHero (HeroDTO hero);

    /**
     * Retrieve list of all heroes from database.
     * @return  list of heroes
     */
    public List<HeroDTO> retrieveAllHeroes ();

    /**
     * Retrieve one hero with unique id.
     * @param id    hero id
     * @return  hero with choosen id
     */
    public HeroDTO retrieveHeroById (long id);

    /**
     * Retrieve one hero with unique name.
     * @param name  hero name
     * @return  hero with choosen name
     */
    public HeroDTO retrieveHeroByName (String name);
    
    /**
     * Delete all heroes from database.
     * 
     * @return true if all heroes were deleted, false otherwise 
     */
    public Boolean deleteAllHeroes();
    
}