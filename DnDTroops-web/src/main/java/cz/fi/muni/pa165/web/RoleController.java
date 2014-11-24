/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.web;

import cz.fi.muni.pa165.dto.RoleDTO;
import cz.fi.muni.pa165.service.RoleService;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
/**
 *
 * @author Dávid
 */
@Controller
@RequestMapping("/Role")
public class RoleController {
    final static Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private MessageSource messageSource;
    
   @ModelAttribute("roles")
    public List<RoleDTO> allRoles() {
        log.debug("allRoles()");
        return roleService.getAllRoles();
       
    }
    
    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public String addRole(Model model) {
        log.debug("allRoles()");
        model.addAttribute("role", new RoleDTO());
        return "addRole";
    }
    
     @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        log.debug("delete({})", id);
        RoleDTO role = roleService.getRoleById(id);
        roleService.deleteRole(role);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("addRole.delete.mess", new Object[]{role.getName(), role.getDescription(), role.getRoleLevel()}, locale)
        );
        return "redirect:" + uriBuilder.path("/Role/addRole").build();
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute RoleDTO role, BindingResult bindingResult, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(locale={}, role={})", locale, role);
        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                log.debug("FieldError: {}", fe);
            }
            return role.getId()< 1?"role/addRole":"role/edit";
        }
        if (role.getId() < 1) {
            roleService.createRole(role);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("role.add.message", new Object[]{role.getName(), role.getDescription(), role.getRoleLevel()}, locale)
            );
        } else {
            roleService.updateRole(role);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("role.updated.message", new Object[]{role.getName(), role.getDescription(), role.getRoleLevel()}, locale)
            );
        }
        return "redirect:" + uriBuilder.path("/Role/addRole").build();
    }
}
