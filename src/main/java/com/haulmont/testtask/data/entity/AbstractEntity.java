/**
 * 
 */
package com.haulmont.testtask.data.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private long id;

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }
}
