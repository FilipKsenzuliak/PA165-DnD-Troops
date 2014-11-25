/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.web;

import cz.fi.muni.pa165.service.MissionService;
import cz.fi.muni.pa165.dto.MissionDTO;
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

/**
 *
 * @author Tomus
 */
@Controller
@RequestMapping("/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String show(HttpServletResponse response) {

        return "TOMUS este viac SEXY";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MissionDTO get(@PathVariable Long id, HttpServletResponse response) {
        MissionDTO mission = missionService.getMissionById(id);
        if (mission == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return mission;
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST, RequestMethod.DELETE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object delete(@PathVariable Long id, HttpServletResponse response) {
        MissionDTO mission = missionService.getMissionById(id);
        if (mission == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            missionService.deleteMission(mission);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object update(@RequestBody @Valid MissionDTO mission, BindingResult bindingResult,
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
        if (mission == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        if (mission.getId() == 0) {
            missionService.createMission(mission);
        } else {
            MissionDTO missionById = missionService.getMissionById(mission.getId());
            if (missionById == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            missionService.updateMission(mission);
        }
        return mission;
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object add(@RequestBody @Valid MissionDTO mission, BindingResult bindingResult,
            UriComponentsBuilder uriBuilder, HttpServletResponse response) throws IOException {
        mission.setId(0L);
        return update(mission, bindingResult, uriBuilder, response);
    }

}
