package com.aditya.istartest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import javax.annotation.PostConstruct;

import com.aditya.istartest.models.mahasiswa.Mahasiswa;
import com.aditya.istartest.models.mahasiswa.MahasiswaService;

@Controller
class MahasiswaViewController {
    MahasiswaService mhsService;
    ResponseStatusException stdException;

    @PostConstruct
    public void init() throws Exception {
        this.mhsService = new MahasiswaService();
        this.stdException = new ResponseStatusException(
            HttpStatus.BAD_GATEWAY,
            "Something wrong happened"
        );
    }

    @GetMapping("/view/mahasiswa/{id}")
    String mahasiswa(Model model, @PathVariable("id") Integer id) {
        try {
            Mahasiswa mhs = this.mhsService.getById(id);
            model.addAttribute("mahasiswa", mhs);
            model.addAttribute("form_method", "PATCH");
            model.addAttribute("form_url", "/mahasiswa/" + id);
            model.addAttribute("delete_visibility", "inherit");
            return "mahasiswa";
        } catch (Exception e ) {
            e.printStackTrace();
            throw this.stdException;
        }
    }

    @GetMapping("/view/mahasiswa")
    String listMahasiswa(Model model, @RequestParam Integer page) {
        try {
            List<Mahasiswa> mhs = this.mhsService.getList(page);
            model.addAttribute("mahasiswas", mhs);
            model.addAttribute("next_link", "/view/mahasiswa?page=" + (page + 1));
            model.addAttribute("prev_link", "/view/mahasiswa?page=" + (page - 1));
            return "mahasiswa_list";
        } catch (Exception e ) {
            e.printStackTrace();
            throw this.stdException;
        }
    }

    @GetMapping("/view/mahasiswa/new")
    String addMahasiswa(Model model) {
        try {
            Mahasiswa newMhs = new Mahasiswa();
            model.addAttribute("mahasiswa", newMhs);
            model.addAttribute("form_method", "POST");
            model.addAttribute("form_url", "/mahasiswa");
            model.addAttribute("delete_visibility", "none");
            return "mahasiswa";
        } catch (Exception e ) {
            e.printStackTrace();
            throw this.stdException;
        }
    }
}