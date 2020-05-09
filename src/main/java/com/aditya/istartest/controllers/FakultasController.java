package com.aditya.istartest.controllers;

import com.aditya.istartest.models.fakultas.Fakultas;
import com.aditya.istartest.models.fakultas.FakultasService;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FakultasController {
	@GetMapping("/fakultas/{id}")
	public Fakultas get(@PathVariable("id") Integer id) {
		try {
			FakultasService fService = new FakultasService();
			return fService.getById(id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, 
				"Fakultas not found",
				e
			);
		}
	}
}