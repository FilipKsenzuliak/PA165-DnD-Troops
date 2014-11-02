/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.converter;
import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.dto.RoleDTO;
import cz.fi.muni.pa165.entity.Hero;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Dávid
 */
public class RoleConverter {
    public static RoleDTO RoleToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setDescription(role.getDescription());
        roleDTO.setName(role.getName());
        roleDTO.setRoleLevel(role.getRoleLevel());
        List<HeroDTO> heroes = null;
        if(role.getHeroes() != null) {
            heroes = new ArrayList<HeroDTO>();
            for(Hero hero : role.getHeroes()) {
                heroes.add(HeroConverter.HeroToHeroDTO(hero));
            }
        }
        roleDTO.setHeroes(heroes);
        return roleDTO;
    }
    
    /*private static <T> List<T> listConvert(List<?> list, Class<T> c) {
        List returnList = new ArrayList();
        boolean listIsHero = false;
        if(c.equals(HeroDTO.class)) {
            listIsHero = true;
        }
        
        for(Object obj : list) {
            returnList.add((Object)(listIsHero ? HeroConverter.HeroToHeroDTO((Hero)obj) : HeroConverter.HeroDTOToHero((HeroDTO) obj)));
        }
        
        return (List<T>) returnList;
    }*/
    
    public static Role RoleDTOtoRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setDescription(roleDTO.getDescription());
        role.setId(role.getId());
        role.setName(roleDTO.getName());
        role.setRoleLevel(roleDTO.getRoleLevel());
        List<Hero> heroes = null;
        if(roleDTO.getHeroes() != null) {
            heroes = new ArrayList<Hero> ();
            for(HeroDTO hero : roleDTO.getHeroes()) {
                heroes.add(HeroConverter.HeroDTOToHero(hero));
            }
        }
        role.setHeroes(heroes);
        return role;
    }
}
