/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165;

import cz.fi.muni.pa165.api.dto.RoleDTO;
import cz.fi.muni.pa165.api.dto.TroopDTO;
import cz.fi.muni.pa165.api.service.TroopService;
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
 * @author Andrej
 */

public class TroopActionBean implements ActionBean{

    private ActionBeanContext context;
    
    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
    
    @SpringBean
    protected TroopService troopService;
    
    // part with displaying of troops
    private List<TroopDTO> troops;
    
    @DefaultHandler
    public Resolution list() {
        troops = troopService.getAllTroops();
        return new ForwardResolution("/troop/list.jsp");
    }

    public List<TroopDTO> getTroops() {
        return troops;
    }

    // adding a troop

    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "amountOfMoney", required = true, minvalue = 0),
    })
    
    @ValidationMethod(on = "add")
    public void createUniqueName(ValidationErrors errors) {
        List<TroopDTO> trp = troopService.getAllTroops();
        if(trp.size() > 0) {
            for(TroopDTO t : trp) {
                if(troop.getName().equals(t.getName())) {
                    errors.add("name", new LocalizableError("troop.save.samenameerror"));
                }
            }
        }
    }
    
    @ValidationMethod(on = "save")
    public void updateUniqueName(ValidationErrors errors) {
        List<TroopDTO> trp = troopService.getAllTroops();
        if(trp.size() > 0) {
            for(TroopDTO t : trp) {
                if((troop.getName().equals(t.getName())) && (troop.getId() != t.getId())) {
                    errors.add("name", new LocalizableError("troop.save.samenameerror"));
                }
            }
        }
    }
    
    private TroopDTO troop;

    public Resolution create() {
        return new ForwardResolution("/troop/create.jsp");
    }

    public Resolution add() {
 
        troopService.createTroop(troop);
        getContext().getMessages().add(new LocalizableMessage("troop.add.message", troop.getName()));
        return new RedirectResolution(this.getClass(), "list");
    }

    public TroopDTO getTroop() {
        return troop;
    }

    public void setTroop(TroopDTO troop) {
        this.troop = troop;
    }

    //--- part for deleting a troop ----

    public Resolution delete() {
        //only id is filled by the form
        troop = troopService.getTroopById(troop.getId());
        troopService.deleteTroop(troop);
        getContext().getMessages().add(new LocalizableMessage("troop.delete.message", troop.getName()));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a troop ----

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadTroopFromDatabase() {
        String ids = getContext().getRequest().getParameter("troop.id");
        if (ids == null) return;
        troop = troopService.getTroopById(Long.parseLong(ids));
    }

    public Resolution edit() {
        return new ForwardResolution("/troop/edit.jsp");
    }

    public Resolution save() {
        troopService.updateTroop(troop);
        return new RedirectResolution(this.getClass(), "list");
    }    
}
