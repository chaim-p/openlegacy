// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.terminal.samples.model;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;

privileged @SuppressWarnings("unused") aspect SignOn_Aspect {

    declare parents: SignOn implements ScreenEntity;
    private String SignOn.focusField;
    
	

	

	

	

	

	

	

	

	

    

    public String SignOn.getCurrentLibrary(){
    	return this.currentLibrary;
    }
    
    public void SignOn.setCurrentLibrary(String currentLibrary){
    	this.currentLibrary = currentLibrary;
    }



    public String SignOn.getDisplay(){
    	return this.display;
    }
    



    public String SignOn.getErrorMessage(){
    	return this.errorMessage;
    }
    



    public String SignOn.getMenu_(){
    	return this.menu_;
    }
    
    public void SignOn.setMenu_(String menu_){
    	this.menu_ = menu_;
    }



    public String SignOn.getPassword(){
    	return this.password;
    }
    
    public void SignOn.setPassword(String password){
    	this.password = password;
    }



    public String SignOn.getProgramprocedure(){
    	return this.programprocedure;
    }
    
    public void SignOn.setProgramprocedure(String programprocedure){
    	this.programprocedure = programprocedure;
    }



    public String SignOn.getSubsystem(){
    	return this.subsystem;
    }
    



    public String SignOn.getSystem(){
    	return this.system;
    }
    



    public String SignOn.getUser(){
    	return this.user;
    }
    
    public void SignOn.setUser(String user){
    	this.user = user;
    }




    public String SignOn.getFocusField(){
    	return focusField;
    }
    public void SignOn.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
}
