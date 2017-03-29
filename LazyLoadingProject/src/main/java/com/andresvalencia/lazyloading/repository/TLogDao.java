package com.andresvalencia.lazyloading.repository;

import com.andresvalencia.lazyloading.domain.TLog;

public interface TLogDao {
    
    public void saveOperation(TLog operation);

}