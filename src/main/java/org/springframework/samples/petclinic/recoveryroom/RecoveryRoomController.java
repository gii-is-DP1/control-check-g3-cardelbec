package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecoveryRoomController {
private static final String VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM = "recoveryroom/createOrUpdateRecoveryRoomForm";
    
    private final RecoveryRoomService recoveryRoomService;
    
    @Autowired
    public RecoveryRoomController(RecoveryRoomService service) {
    	this.recoveryRoomService = service;
    }
    
    @GetMapping("recoveryroom/create")
    public String initCreateRecoveryRoomForm(Model model) {
    	RecoveryRoom recoveryRoom = new RecoveryRoom();
    	List<RecoveryRoomType> types = this.recoveryRoomService.getAllRecoveryRoomTypes();
    	model.addAttribute(recoveryRoom);
    	model.addAttribute(types);
    	return VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("recoveryroom/create")
    public String processCreateRecoveryRoomForm(@Valid RecoveryRoom recoveryRoom, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		model.addAttribute(recoveryRoom);
    		model.addAttribute(this.recoveryRoomService.getAllRecoveryRoomTypes());
    		model.addAttribute(result.getAllErrors());
    		return VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM;
    	}else {
    		this.recoveryRoomService.save(recoveryRoom);
    	}
    	return "welcome";
    }
}
