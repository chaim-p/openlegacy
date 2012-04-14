// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.terminal.samples.model;

import java.util.*;
import org.openlegacy.terminal.ScreenEntity;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

privileged @SuppressWarnings("unused") aspect WorkWithItemMaster2_Aspect {
    
    declare @type: WorkWithItemMaster2 : @Component;
	declare @type: WorkWithItemMaster2 : @Scope("prototype");
    

    declare parents: WorkWithItemMaster2 implements ScreenEntity;
    private String WorkWithItemMaster2.focusField;
    
	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

	

    

    public String WorkWithItemMaster2.getAmendedDate(){
    	return this.amendedDate;
    }
    



    public String WorkWithItemMaster2.getCreatedBy(){
    	return this.createdBy;
    }
    



    public String WorkWithItemMaster2.getCreatedDate(){
    	return this.createdDate;
    }
    



    public String WorkWithItemMaster2.getField(){
    	return this.field;
    }
    
    public void WorkWithItemMaster2.setField(String field){
    	this.field = field;
    }



    public String WorkWithItemMaster2.getField1(){
    	return this.field1;
    }
    
    public void WorkWithItemMaster2.setField1(String field1){
    	this.field1 = field1;
    }



    public String WorkWithItemMaster2.getField2(){
    	return this.field2;
    }
    
    public void WorkWithItemMaster2.setField2(String field2){
    	this.field2 = field2;
    }



    public Integer WorkWithItemMaster2.getItemNumberdesc(){
    	return this.itemNumberdesc;
    }
    



    public String WorkWithItemMaster2.getItemTypeBusinessArea(){
    	return this.itemTypeBusinessArea;
    }
    
    public void WorkWithItemMaster2.setItemTypeBusinessArea(String itemTypeBusinessArea){
    	this.itemTypeBusinessArea = itemTypeBusinessArea;
    }



    public Integer WorkWithItemMaster2.getListPrice(){
    	return this.listPrice;
    }
    
    public void WorkWithItemMaster2.setListPrice(Integer listPrice){
    	this.listPrice = listPrice;
    }



    public String WorkWithItemMaster2.getNlCostOfSalesAccount(){
    	return this.nlCostOfSalesAccount;
    }
    
    public void WorkWithItemMaster2.setNlCostOfSalesAccount(String nlCostOfSalesAccount){
    	this.nlCostOfSalesAccount = nlCostOfSalesAccount;
    }



    public String WorkWithItemMaster2.getNlSalesAccount(){
    	return this.nlSalesAccount;
    }
    
    public void WorkWithItemMaster2.setNlSalesAccount(String nlSalesAccount){
    	this.nlSalesAccount = nlSalesAccount;
    }



    public String WorkWithItemMaster2.getNlStockAccount(){
    	return this.nlStockAccount;
    }
    
    public void WorkWithItemMaster2.setNlStockAccount(String nlStockAccount){
    	this.nlStockAccount = nlStockAccount;
    }



    public Integer WorkWithItemMaster2.getStandardUnitCost(){
    	return this.standardUnitCost;
    }
    
    public void WorkWithItemMaster2.setStandardUnitCost(Integer standardUnitCost){
    	this.standardUnitCost = standardUnitCost;
    }



    public String WorkWithItemMaster2.getStockAnalysisCode(){
    	return this.stockAnalysisCode;
    }
    
    public void WorkWithItemMaster2.setStockAnalysisCode(String stockAnalysisCode){
    	this.stockAnalysisCode = stockAnalysisCode;
    }



    public String WorkWithItemMaster2.getStockInventoryGroup(){
    	return this.stockInventoryGroup;
    }
    
    public void WorkWithItemMaster2.setStockInventoryGroup(String stockInventoryGroup){
    	this.stockInventoryGroup = stockInventoryGroup;
    }



    public String WorkWithItemMaster2.getStockValueGroup(){
    	return this.stockValueGroup;
    }
    
    public void WorkWithItemMaster2.setStockValueGroup(String stockValueGroup){
    	this.stockValueGroup = stockValueGroup;
    }




    public String WorkWithItemMaster2.getFocusField(){
    	return focusField;
    }
    public void WorkWithItemMaster2.setFocusField(String focusField){
    	this.focusField = focusField;
    }
    
}
