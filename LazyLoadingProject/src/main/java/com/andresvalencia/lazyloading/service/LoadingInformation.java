package com.andresvalencia.lazyloading.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class LoadingInformation {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
        
    @NotEmpty(message="No puede ser vacio")
    private String cedula;
    
    @NotEmpty(message="No puede ser vacio")
    private String pathFile;

    public String getPercentage() {
        return cedula;
    }

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
		logger.info("Cedula : " + cedula);
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
		logger.info("Ruta Archivo : " + pathFile);
	}
           
}