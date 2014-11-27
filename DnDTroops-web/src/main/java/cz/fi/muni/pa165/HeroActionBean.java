/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165;

import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.dto.RoleDTO;
import cz.fi.muni.pa165.dto.TroopDTO;
import cz.fi.muni.pa165.entity.Race;
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
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    @SpringBean
    protected HeroService heroService;
    
    private List<HeroDTO> heroes = new ArrayList(nieco());
    private String jano = "cau";

    public String getJano() {
        return jano;
    }

    public void setJano(String jano) {
        this.jano = jano;
    }

    public List<HeroDTO> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<HeroDTO> heroes) {
        this.heroes = heroes;
    }
    
    @DefaultHandler
    public Resolution list() {
        TroopDTO troop = new TroopDTO();
        RoleDTO role = new RoleDTO();                
        List<RoleDTO> roles = new ArrayList();
        roles.add(role);
        
        HeroDTO hero = new HeroDTO(Race.ELF, 150L, 10L, "Jozef",  troop, roles);
        HeroDTO heroo = new HeroDTO(Race.ELF, 1500L, 100L, "Jozefo",  troop, roles);
        heroes.add(hero);
        heroes.add(heroo);
             
        return new ForwardResolution("heroo.jsp");
    } 
    
    public List<HeroDTO> nieco(){
        List<HeroDTO> h = new ArrayList();
        TroopDTO troop = new TroopDTO();
        RoleDTO role = new RoleDTO();                
        List<RoleDTO> roles = new ArrayList();
        roles.add(role);
        
        HeroDTO hero = new HeroDTO(Race.ELF, 150L, 10L, "Jozef",  troop, roles);
        HeroDTO heroo = new HeroDTO(Race.ELF, 1500L, 100L, "Jozefo",  troop, roles);
        h.add(hero);
        h.add(heroo);
        return h;
    }
}
