package com.andresvalencia.lazyloading.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andresvalencia.lazyloading.domain.TLog;
import com.andresvalencia.lazyloading.repository.TLogDao;

/**
 * @author andres.valencia
 * Clase encargada de implementar la logica para el calculo del maximo número de viajes
 */
@Component
public class MovementManagerModel implements MovementManagerInterface {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TLogDao tlogDao;
	
	public void setTlogDao(TLogDao tlogDao) {
		this.tlogDao = tlogDao;
	}

	@Override
	public byte[] getMaximumNumberMovements(String cedula, String pathFile) throws Exception {
				
		writeLog(cedula);
		
		List<Integer> arrayFile = getFileToArray(pathFile);		
		String outPutString= "";
		List<Integer> arrayAux;
		int numeroElementosDia;
		int acumuladorDias = 1;
		
		if(arrayFile.get(0)	< 1 || arrayFile.get(0)	> 500 ){
			throw new Exception("Número de dias de trabajo fuera de rango");
		}
		
		for(int dias=1; dias <= arrayFile.get(0) ; dias ++){
			arrayAux = new ArrayList<>();
			
			numeroElementosDia = arrayFile.get(acumuladorDias);
			
			if(numeroElementosDia	< 1 || numeroElementosDia	> 100 ){
				throw new Exception("Número de elementos fuera de rango, para el día: " + dias);
			}	
			
			for( int i=1; i <= numeroElementosDia; i++ ){
				
				if(arrayFile.get(acumuladorDias + i) < 1 || arrayFile.get(acumuladorDias + i) > 100 ){
					throw new Exception("Peso del elemento " + arrayFile.get(acumuladorDias + i) + " fuera de rango");
				}	
				
				arrayAux.add(arrayFile.get(acumuladorDias + i));								 											
			}
			
			arrayAux.sort(Comparator.reverseOrder());			
			outPutString = outPutString + "Case #" + dias + ": " + getNumberTrip(arrayAux) + "\n";
			acumuladorDias = acumuladorDias + numeroElementosDia + 1;
		}
																		  	        	   		
		return outPutString.getBytes();
	}

	private int getNumberTrip(List<Integer> arrayAux) {
		
		int numeroDeViajes=0;
		
		List<Integer> arrayTrip; 
		while(! arrayAux.isEmpty()){
			
			arrayTrip = new ArrayList<>();
			arrayTrip.add(arrayAux.get(0));
			numeroDeViajes = numeroDeViajes + getTripsRec(arrayTrip, arrayAux);
			arrayAux.remove(0);														
		}

		return  numeroDeViajes;
	}

	private int getTripsRec(List<Integer> arrayTrip, List<Integer> arrayAux) {
		
		if( ( arrayTrip.size() * arrayTrip.get(0) ) >= 50 ){
			return 1;
		}else{
			if ( arrayAux.size() > 1){
				arrayTrip.add(arrayAux.get(arrayAux.size()-1));
				arrayAux.remove(arrayAux.size()-1);
			}else{
				return 0;
			}						
							
			return  getTripsRec(arrayTrip, arrayAux);
		}					
	}

	private List<Integer> getFileToArray(String pathFile) throws IOException {
		
		String line;
		List<Integer> arrayFile = new ArrayList<>();
		FileReader file = new FileReader(pathFile);
		BufferedReader buffer = new BufferedReader(file);
		
		while( (line = buffer.readLine())!= null  ){
			arrayFile.add( Integer.parseInt(line ));
		}
		buffer.close();
		
		return arrayFile;
	}

	private void writeLog(String cedula) {
		TLog itemTtlog = new TLog();		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		itemTtlog.setCedulaParticipante(cedula);
		itemTtlog.setDateExecution(sdf.format(new Date()));					
		tlogDao.saveOperation(itemTtlog);
	}
}