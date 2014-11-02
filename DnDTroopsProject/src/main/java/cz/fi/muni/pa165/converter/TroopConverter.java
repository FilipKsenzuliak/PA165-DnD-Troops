/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.converter;

import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.dto.TroopDTO;
import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.entity.Troop;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej
 */
public class TroopConverter {
    public static TroopDTO TroopToTroopDTO(Troop troop) {
        TroopDTO troopDTO = new TroopDTO();
        troopDTO.setId(troop.getId());
        troopDTO.setAmountOfMoney(troop.getAmountOfMoney());
        troopDTO.setName(troop.getName());
        troopDTO.setMission(troop.getMission());
        List<HeroDTO> heroes = null;
        if(troop.getHeroes() != null) {
            heroes = new ArrayList<HeroDTO>();
            for(Hero hero : troop.getHeroes()) {
                heroes.add(HeroConverter.HeroToHeroDTO(hero));
            }
        }
        troopDTO.setHeroes(heroes);
        return troopDTO;
    }
    
    public static Troop TroopDTOtoTroop(TroopDTO troopDTO) {
        Troop troop = new Troop();
        troop.setAmountOfMoney(troopDTO.getAmountOfMoney());
        troop.setId(troopDTO.getId());
        troop.setName(troopDTO.getName());
        troop.setMission(troopDTO.getMission());
        List<Hero> heroes = null;
        if(troopDTO.getHeroes() != null) {
            heroes = new ArrayList<Hero> ();
            for(HeroDTO hero : troopDTO.getHeroes()) {
                heroes.add(HeroConverter.HeroDTOToHero(hero));
            }
        }
        troop.setHeroes(heroes);
        return troop;
    }
}
