/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;
import cz.fi.muni.pa165.entity.Hero;
import java.util.List;

/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */
public interface HeroDAO {
    /**
     * Add new hero into database. Id is automatically generated and
     * stored into id attribute.
     *
     * @param hero hero to be created.
     * @throws IllegalArgumentException when hero is null, hero already has id assigned
     */
    public void createHero(Hero hero) throws IllegalArgumentException;
    
   /**
     * Return hero with given id.
     *
     * @param id primary key of hero.
     * @return hero with given id or null if such hero is not in database.
     * @throws IllegalArgumentException when given id is null.
     */
    public Hero getHero(Long id) throws IllegalArgumentException;
    
    /**
     * Updates hero in database.
     *
     * @param hero hero to be updated.
     * @throws IllegalArgumentException when hero is null, or has null id.
     */
    public void updateHero(Hero hero) throws IllegalArgumentException;
    
    /**
     * Remove hero from database.
     *
     * @param hero hero to be removed from database.
     * @throws IllegalArgumentException when hero is null, or has null id.
     */
    public void removeHero(Hero hero) throws IllegalArgumentException;
    
    /**
     * Return list of all heroes in the database.
     *
     * @return list of all heroes.
     */
    public List<Hero> getAllHeroes();
    
    /**
     * Method to find hero by name.
     * 
     * @param name name to find hero
     * @return list of all heroes with given name.
     * @throws IllegalArgumentException when given name is null.
     */
    public List<Hero> findHeroByName(String Name) throws IllegalArgumentException;
}
