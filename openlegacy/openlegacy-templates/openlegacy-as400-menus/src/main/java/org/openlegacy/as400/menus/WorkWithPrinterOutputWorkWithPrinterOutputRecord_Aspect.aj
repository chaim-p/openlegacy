// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.as400.menus;

import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openlegacy.as400.menus.WorkWithPrinterOutput.WorkWithPrinterOutputRecord;

privileged @SuppressWarnings("unused") aspect WorkWithPrinterOutputRecord_Aspect {
    
    public String WorkWithPrinterOutputRecord.getOpt(){
    	return this.opt;
    }
    

    public String WorkWithPrinterOutputRecord.getOutput(){
    	return this.output;
    }
    

    public String WorkWithPrinterOutputRecord.getStatus(){
    	return this.status;
    }
    


    public int WorkWithPrinterOutputRecord.hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean WorkWithPrinterOutputRecord.equals(Object other){
    	// TODO exclude terminal fields
		return EqualsBuilder.reflectionEquals(this,other);
    }
}
