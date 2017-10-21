/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderank.io.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author WFG-Greg
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyExistException extends RuntimeException{

	public EntityAlreadyExistException(String entity) {
		super(entity);
	}

}
