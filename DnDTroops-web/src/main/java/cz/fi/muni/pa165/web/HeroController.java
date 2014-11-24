/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.service.HeroService;
import cz.fi.muni.pa165.serviceImpl.HeroServiceImpl;

/**
 *
 * @author Filip
 */
@Controller
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<HeroDTO> show(HttpServletResponse response) {
        return heroService.getAllHeroes();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HeroDTO get(@PathVariable Long id, HttpServletResponse response) {
        HeroDTO hero = heroService.getHeroById(id);
        if (hero == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return hero;
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST, RequestMethod.DELETE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        HeroDTO hero = heroService.getHeroById(id);
        if (hero == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            heroService.deleteHero(hero);
        }
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void update(@RequestBody @Valid HeroDTO hero, BindingResult bindingResult,
                         UriComponentsBuilder uriBuilder, HttpServletResponse response) throws IOException {
        if(hero==null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(hero.getId()==0) {
            heroService.createHero(hero);
        } else {
            HeroDTO heroById = heroService.getHeroById(hero.getId());
            if (heroById == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            heroService.updateHero(hero);
        }
        //redirect not allowed for Cross-Origin Request with Preflight, return updated data directly
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody @Valid HeroDTO hero, BindingResult bindingResult,
                      UriComponentsBuilder uriBuilder, HttpServletResponse response) throws IOException {
        hero.setId(0L);
    }
}