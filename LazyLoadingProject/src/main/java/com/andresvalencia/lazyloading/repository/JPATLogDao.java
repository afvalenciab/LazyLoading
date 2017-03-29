package com.andresvalencia.lazyloading.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.andresvalencia.lazyloading.domain.TLog;

@Repository(value = "tlogDao")
public class JPATLogDao implements TLogDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public void saveOperation(TLog operation) {        
    	em.persist(operation);    	
    }

}