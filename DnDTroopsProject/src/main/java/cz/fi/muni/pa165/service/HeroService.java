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
     * Adds hero to DB.
     * @param hero 
     */
    void createHero(HeroDTO hero);
    /**
     * Updates hero's values in DB
     * @param hero 
     */
    void updateHero(HeroDTO hero);  
    /**
     * Deletes hero from DB
     * @param hero 
     */
    void deleteHero(HeroDTO hero);   
    /**
     * Deletes all heroes in DB.
     */
    void deleteAllHeroes();    
    /**
     * Updates heroes from list.
     * @param heroes
     */
    void updateHeroes(List<HeroDTO> heroes);
    /**
     * Gets all heroes loaded in DB
     * @return List of heroes
     */
    List<HeroDTO> getAllHeroes();  
    /**
     * Finds hero by its Id
     * @param id
     * @return hero with specific id
     */
    HeroDTO getHeroById(Long id);
    /**
     * Finds hero by its Id
     * @param name
     * @return heroes with specific name
     */
    List<HeroDTO> getHeroByName(String name);

}
