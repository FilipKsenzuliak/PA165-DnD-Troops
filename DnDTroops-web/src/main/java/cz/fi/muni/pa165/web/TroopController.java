/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.web;

/**
 *
 * @author Andrej
 */
import cz.fi.muni.pa165.service.TroopService;
import cz.fi.muni.pa165.dto.TroopDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/troop")
public class TroopController {

    @Autowired
    private TroopService troopService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String show(HttpServletResponse response) {

        return "Ne moc SEXI";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TroopDTO get(@PathVariable Long id, HttpServletResponse response) {
        TroopDTO troop = troopService.getTroopById(id);
        if (troop == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return troop;
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST, RequestMethod.DELETE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object delete(@PathVariable Long id, HttpServletResponse response) {
        TroopDTO troop = troopService.getTroopById(id);
        if (troop == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            troopService.deleteTroop(troop);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object update(@RequestBody @Valid TroopDTO troop, BindingResult bindingResult,
            UriComponentsBuilder uriBuilder, HttpServletResponse response) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            if (bindingResult.hasGlobalErrors()) {
                Map<String, String> fes = new HashMap<>();
                for (ObjectError ge : bindingResult.getGlobalErrors()) {
                    fes.put(ge.getObjectName(), ge.getDefaultMessage());
                }
                map.put("globalErrors", fes);
            }
            if (bindingResult.hasFieldErrors()) {
                Map<String, String> fes = new HashMap<>();
                for (FieldError fe : bindingResult.getFieldErrors()) {
                    fes.put(fe.getField(), fe.getDefaultMessage());
                }
                map.put("fieldErrors", fes);
            }
            response.setStatus(422);
            return map;
        }
        if (troop == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        if (troop.getId() == 0) {
            troopService.createTroop(troop);
        } else {
            TroopDTO missionById = troopService.getTroopById(troop.getId());
            if (missionById == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            troopService.updateTroop(troop);
        }
        return troop;
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object add(@RequestBody @Valid TroopDTO troop, BindingResult bindingResult,
            UriComponentsBuilder uriBuilder, HttpServletResponse response) throws IOException {
        troop.setId(0L);
        return update(troop, bindingResult, uriBuilder, response);
    }

}
