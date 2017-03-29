package com.andresvalencia.lazyloading.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tlog") 
public class TLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cedulaParticipante;
    private String dateExecution;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCedulaParticipante() {
		return cedulaParticipante;
	}
	public void setCedulaParticipante(String cedulaParticipante) {
		this.cedulaParticipante = cedulaParticipante;
	}
	public String getDateExecution() {
		return dateExecution;
	}
	public void setDateExecution(String dateExecution) {
		this.dateExecution = dateExecution;
	}
	@Override
	public String toString() {
		return "TLog [cedulaParticipante=" + cedulaParticipante + ", dateExecution=" + dateExecution + "]";
	}


	

    
}