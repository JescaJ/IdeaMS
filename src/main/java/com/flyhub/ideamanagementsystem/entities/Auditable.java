package com.flyhub.ideamanagementsystem.entities;

import java.util.Date;
import static javax.persistence.TemporalType.TIMESTAMP;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"created_by_global_user_id", "modified_by_global_user_id"},
        allowGetters = true
)
public abstract class Auditable<U> {

    @CreatedBy
    @Column(updatable = false)
    protected U created_by_global_user_id;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TIMESTAMP)
    protected Date create_date;

    @LastModifiedBy
    protected U modified_by_global_user_id;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date modified_date;

    
    // getters and setter here
	public U getCreated_by_global_user_id() {
		return created_by_global_user_id;
	}

	public void setCreated_by_global_user_id(U created_by_global_user_id) {
		this.created_by_global_user_id = created_by_global_user_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public U getModified_by_global_user_id() {
		return modified_by_global_user_id;
	}

	public void setModified_by_global_user_id(U modified_by_global_user_id) {
		this.modified_by_global_user_id = modified_by_global_user_id;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
       
	
    
    
}