/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165;

import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.dto.RoleDTO;
import cz.fi.muni.pa165.dto.TroopDTO;
import cz.fi.muni.pa165.entity.Race;
import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.entity.Troop;
import cz.fi.muni.pa165.service.HeroService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author Filip
 */
public class HeroActionBean implements ActionBean{
    
    private ActionBeanContext context;
    
    @Override
    public ActionBeanContext getContext() { return context; }
    @Override
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    @SpringBean
    protected HeroService heroService;
    
    private HeroDTO hero;
    private String name;
    private Race race;
    private Long age;
    private Long rank;
    private List<Role> role;
    private Troop troop;
    private List<HeroDTO> heroes = new ArrayList(startHero());
    private List<RoleDTO> roles = new ArrayList(startRole());
    private List<TroopDTO> troops = new ArrayList(startTroop());

    public List<HeroDTO> getHeroes() { return heroes; }
    public void setHeroes(List<HeroDTO> heroes) { this.heroes = heroes; }
    
    public List<RoleDTO> getRoles() { return roles; }
    public void setRoles(List<RoleDTO> roles) { this.roles = roles; }
    
    public List<TroopDTO> getTroops() { return troops; }
    public void setTroops(List<TroopDTO> troops) { this.troops = troops; }

    public HeroService getHeroService() { return heroService; }
    public void setHeroService(HeroService heroService) { this.heroService = heroService; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Race getRace() { return race; }
    public void setRace(Race race) { this.race = race; }

    public Long getAge() { return age; }
    public void setAge(Long age) { this.age = age; }

    public Long getRank() { return rank; }
    public void setRank(Long rank) { this.rank = rank; }

    public List<Role> getRole() { return role; }
    public void setRole(List<Role> role) { this.role = role; }

    public Troop getTroop() { return troop; }
    public void setTroop(Troop troop) { this.troop = troop; }
    
    public String result;
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    
    public Resolution addition() {
        result = getName()+getAge().toString()+getRace().toString();
        return new ForwardResolution("/index.jsp");
    }
    
    @DefaultHandler
    public Resolution list() {
        heroes = heroService.getAllHeroes();
        return new ForwardResolution("heroo.jsp");
    } 
    
// *** Testing datas ***
    
    public List<HeroDTO> startHero(){
        TroopDTO troop = new TroopDTO("Mathemagicians", null, 5000L, null);
        RoleDTO role = new RoleDTO(null,"Magician","making some spells");
        RoleDTO role2 = new RoleDTO(null,"Warrior","making some spells");
        RoleDTO role3 = new RoleDTO(null,"Paladin","making some spells");
        List<RoleDTO> roles = new ArrayList();
        List<RoleDTO> roles2 = new ArrayList();
        List<RoleDTO> roles3 = new ArrayList();
        roles.add(role);
        roles.add(role2);
        roles2.add(role2);
        roles3.add(role3);
        
        HeroDTO hero = new HeroDTO(1L,Race.ELF, 210L, 7L, "Jozef Kopac",  troop, roles);
        HeroDTO hero2 = new HeroDTO(2L,Race.DWARF, 150L, 5L, "Peter Kopac",  troop, roles2);
        HeroDTO hero3 = new HeroDTO(3L,Race.HUMAN, 32L, 9L, "Peter Kovac",  troop, roles2);
        List<HeroDTO> h = new ArrayList();
        
        h.add(hero);
        h.add(hero2);
        h.add(hero3);
        
        return h;
    }
    
    public List<RoleDTO> startRole(){
        RoleDTO role = new RoleDTO(null,"Magician","making some spells");
        RoleDTO role2 = new RoleDTO(null,"Warrior","making some damages");
        RoleDTO role3 = new RoleDTO(null,"Paladin","beeing holy");        
        List<RoleDTO> r = new ArrayList();
        
        r.add(role);
        r.add(role2);
        r.add(role3);
                
        return r;
    }
    
    public List<TroopDTO> startTroop(){
        TroopDTO troop = new TroopDTO("Mathemagicians", null, 5000L, null);
        TroopDTO troop2 = new TroopDTO("Horses", null, 5000L, null);
        TroopDTO troop3 = new TroopDTO("All in one", null, 5000L, null);
        List<TroopDTO> t = new ArrayList();
        
        t.add(troop);
        t.add(troop2);
        t.add(troop3);
        
        return t;
    }
    
    public Resolution create() {
        //HeroDTO hero = new HeroDTO(race,);
        //heroService.createHero(hero);
        return new ForwardResolution("/hero.jsp");
    }
}
