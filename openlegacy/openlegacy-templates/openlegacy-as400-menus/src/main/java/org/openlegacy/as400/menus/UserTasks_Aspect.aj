// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.menus;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;

privileged @SuppressWarnings("unused") aspect UserTasks_Aspect {

    declare parents: UserTasks implements ScreenEntity;
    private String UserTasks.focusField;
    
	

	

    

    public String UserTasks.getMenuSelection(){
    	return this.menuSelection;
    }
    
    public void UserTasks.setMenuSelection(String menuSelection){
    	this.menuSelection = menuSelection;
    }



    public String UserTasks.getSystem(){
    	return this.system;
    }
    




    public String UserTasks.getFocusField(){
    	return focusField;
    }
    public void UserTasks.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
}