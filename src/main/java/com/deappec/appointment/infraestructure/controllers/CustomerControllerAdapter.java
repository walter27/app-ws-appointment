package com.deappec.appointment.infraestructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deappec.appointment.application.services.CustomerService;
import com.deappec.appointment.infraestructure.controllers.mappers.CustomerControllerMapper;
import com.deappec.appointment.infraestructure.models.CustomerDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerControllerAdapter {
	
	private final CustomerService service;
	private final CustomerControllerMapper mapper;
	
	@PostMapping
	public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto){
		  return ResponseEntity.status(HttpStatus.CREATED)
			        .body(mapper.toCustomer(
			            service.save(mapper.toCustomerDto(customerDto))));
	}

}
