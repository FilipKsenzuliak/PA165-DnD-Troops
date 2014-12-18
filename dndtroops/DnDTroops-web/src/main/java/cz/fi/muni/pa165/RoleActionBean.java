/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165;

import cz.fi.muni.pa165.api.dto.RoleDTO;
import cz.fi.muni.pa165.api.service.RoleService;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Dávid
 */

public class RoleActionBean implements ActionBean{

    private ActionBeanContext context;
    
    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
    
    @SpringBean
    protected RoleService roleService;
    
    // part with displaying of roles
    private List<RoleDTO> roles;
    
    @DefaultHandler
    public Resolution list() {
        roles = roleService.getAllRoles();
        return new ForwardResolution("/role/list.jsp");
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    // adding a role

    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "description", required = true),
            @Validate(on = {"add", "save"}, field = "roleLevel", required = true, minvalue = 0)
    })
    
    @ValidationMethod(on = "add")
    public void createUniqueName(ValidationErrors errors) {
        List<RoleDTO> rls = roleService.getAllRoles();
        if(rls.size() > 0) {
            for(RoleDTO r : rls) {
                if(role.getName().equals(r.getName())) {
                    errors.add("name", new LocalizableError("role.save.samenameerror"));
                }
            }
        }
    }
    
    @ValidationMethod(on = "save")
    public void updateUniqueName(ValidationErrors errors) {
        List<RoleDTO> rls = roleService.getAllRoles();
        if(rls.size() > 0) {
            for(RoleDTO r : rls) {
                if((role.getName().equals(r.getName())) && (role.getId() != r.getId())) {
                    errors.add("name", new LocalizableError("role.save.samenameerror"));
                }
            }
        }
    }
    
    private RoleDTO role;

    public Resolution create() {
        return new ForwardResolution("/role/create.jsp");
    }

    public Resolution add() {
 
        roleService.createRole(role);
        getContext().getMessages().add(new LocalizableMessage("role.add.message", role.getName()));
        return new RedirectResolution(this.getClass(), "list");
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    //--- part for deleting a role ----

    public Resolution delete() {
        //only id is filled by the form
        role = roleService.getRoleById(role.getId());
        roleService.deleteRole(role);
        getContext().getMessages().add(new LocalizableMessage("role.delete.message", role.getName()));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a role ----

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadRoleFromDatabase() {
        String ids = getContext().getRequest().getParameter("role.id");
        if (ids == null) return;
        role = roleService.getRoleById(Long.parseLong(ids));
    }

    public Resolution edit() {
        return new ForwardResolution("/role/edit.jsp");
    }

    public Resolution save() {
        roleService.updateRole(role);
        return new RedirectResolution(this.getClass(), "list");
    }    
}
