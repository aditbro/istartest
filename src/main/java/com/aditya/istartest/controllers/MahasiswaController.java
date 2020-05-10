package com.aditya.istartest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.aditya.istartest.models.mahasiswa.Mahasiswa;
import com.aditya.istartest.models.mahasiswa.MahasiswaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MahasiswaController {
	@GetMapping("/mahasiswa/{id}")
	public Mahasiswa get(@PathVariable("id") Integer id, HttpServletRequest request) {
		try {
            LoginController.authenticate(request.getCookies());
			MahasiswaService mService = new MahasiswaService();
			return mService.getById(id);
		} catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Something wrong happened",
                e
            );
        } 
    }

    @GetMapping("/mahasiswa")
	public List<Mahasiswa> getList(@RequestParam Integer page, HttpServletRequest request) {
		try {
            LoginController.authenticate(request.getCookies());
			MahasiswaService mService = new MahasiswaService();
			return mService.getList(page);
		} catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Something wrong happened",
                e
            );
        } 
    }

    @PostMapping("/mahasiswa")
	public ResponseEntity post(@RequestBody Mahasiswa mhs, HttpServletRequest request) {
		try {
            LoginController.authenticate(request.getCookies());
			MahasiswaService mhsService = new MahasiswaService();
            mhsService.add(mhs);
            return ResponseEntity.ok(HttpStatus.OK);
		} catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Something wrong happened",
                e
            );
        } 
    }

    @PatchMapping("/mahasiswa/{id}")
	public ResponseEntity patch(@RequestBody Mahasiswa mhs, HttpServletRequest request) {
		try {
            LoginController.authenticate(request.getCookies());
			MahasiswaService mhsService = new MahasiswaService();
            mhsService.update(mhs);
            return ResponseEntity.ok(HttpStatus.OK);
		} catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Something wrong happened",
                e
            );
        } 
    }

    @DeleteMapping("/mahasiswa/{id}")
	public ResponseEntity delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		try {
            LoginController.authenticate(request.getCookies());
			MahasiswaService mhsService = new MahasiswaService();
            mhsService.delete(id);
            return ResponseEntity.ok(HttpStatus.OK);
		} catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                HttpStatus.BAD_GATEWAY,
                "Something wrong happened",
                e
            );
        } 
    }
}