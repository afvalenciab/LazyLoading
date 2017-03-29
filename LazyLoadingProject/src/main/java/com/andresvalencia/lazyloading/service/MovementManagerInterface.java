package com.andresvalencia.lazyloading.service;

import java.io.Serializable;

@FunctionalInterface
public interface MovementManagerInterface extends Serializable {
	    
    public byte[] getMaximumNumberMovements(String cedula, String pathFile) throws  Exception;

}