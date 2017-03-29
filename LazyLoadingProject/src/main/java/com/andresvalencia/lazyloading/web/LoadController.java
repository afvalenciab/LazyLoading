package com.andresvalencia.lazyloading.web;


import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.andresvalencia.lazyloading.service.LoadingInformation;
import com.andresvalencia.lazyloading.service.MovementManagerInterface;


/**
 * @author andres.valencia
 * Clase encargada de orquestar las peticiones de la vista
 */
@Controller
@RequestMapping(value="/LazyLoading.htm")
public class LoadController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private MovementManagerInterface movementManager;       
    
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid LoadingInformation loadingInformation, BindingResult result, HttpServletResponse response,HttpServletRequest request ) throws IOException
    {    		        	    	
        if (result.hasErrors()) {
        	logger.info("Error en los parametros");
            return "LazyLoading";
        }
		                        
        logger.info("Cedula: "+ loadingInformation.getCedula() + "  Ruta: " + loadingInformation.getPathFile());                                                       
        
        try {
        	
        	byte[] outPutByte =  movementManager.getMaximumNumberMovements(loadingInformation.getCedula(), loadingInformation.getPathFile());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename=lazy_loading_output.txt");
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(outPutByte);
            ouputStream.flush();
            ouputStream.close();
            
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}  catch(Exception ex){
			result.rejectValue("pathFile","Range",ex.getMessage());
			return "LazyLoading";
		}                      
        
        return "redirect:/LazyLoading.htm";
    }

    @RequestMapping(method = RequestMethod.GET)
    protected LoadingInformation formBackingObject(HttpServletRequest request) throws ServletException {    	
    	logger.info("Inicia página principal LazyLoading.jsp ");                   
        return new LoadingInformation();        
    }

	public void setMovementManager(MovementManagerInterface movementManager) {
		this.movementManager = movementManager;
	}

	public MovementManagerInterface getMovementManager() {
		return movementManager;
	}
	
	
}