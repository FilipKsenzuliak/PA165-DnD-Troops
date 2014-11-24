/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.web;

import cz.fi.muni.pa165.dto.RoleDTO;
import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.service.RoleService;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
/**
 *
 * @author Dávid
 */
@Controller
@RequestMapping("/role")
public class RoleController {
   final static Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<RoleDTO> show(HttpServletResponse response) {
        log.debug("list()");
        return roleService.getAllRoles();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RoleDTO get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("get({})", id);
        RoleDTO role = roleService.getRoleById(id);
        if (role == null) {
            log.warn("role {} not found", id);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return role;
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST, RequestMethod.DELETE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object delete(@PathVariable Long id, HttpServletResponse response) {
        log.debug("delete({})", id);
        RoleDTO role = roleService.getRoleById(id);
        if (role == null) {
            log.warn("role {} not found", id);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            roleService.deleteRole(role);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object update(@RequestBody @Valid RoleDTO role, BindingResult bindingResult,
                         UriComponentsBuilder uriBuilder, HttpServletResponse response) throws IOException {
        log.debug("update({})", role);
        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            Map<String,Object> map = new HashMap<>();
            if(bindingResult.hasGlobalErrors()) {
                Map<String,String> fes = new HashMap<>();
                for (ObjectError ge : bindingResult.getGlobalErrors()) {
                     fes.put(ge.getObjectName(), ge.getDefaultMessage());
                }
                map.put("globalErrors",fes);
            }
            if(bindingResult.hasFieldErrors()) {
                Map<String,String> fes = new HashMap<>();
                for (FieldError fe : bindingResult.getFieldErrors()) {
                    fes.put(fe.getField(),fe.getDefaultMessage());
                }
                map.put("fieldErrors",fes);
            }
            log.debug("returns map={}",map);
            response.setStatus(422);
            return map;
        }
        if(role==null) {
            log.debug("null input");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        if(role.getId()==0) {
            roleService.createRole(role);
        } else {
            RoleDTO roleById = roleService.getRoleById(role.getId());
            if (roleById == null) {
                log.warn("role {} not found", role.getId());
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            roleService.updateRole(role);
        }
        return role;
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.PUT},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object add(@RequestBody @Valid RoleDTO role, BindingResult bindingResult,
                      UriComponentsBuilder uriBuilder, HttpServletResponse response) throws IOException {
        log.debug("add({})",role);
        role.setId(0L);
        return update(role,bindingResult,uriBuilder,response);
    }

}
