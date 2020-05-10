package com.aditya.istartest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aditya.istartest.models.jurusan.Jurusan;
import com.aditya.istartest.models.jurusan.JurusanService;
import com.aditya.istartest.models.mahasiswa.Mahasiswa;
import com.aditya.istartest.models.mahasiswa.MahasiswaService;

@Controller
class MahasiswaViewController extends HandlerInterceptorAdapter {
    MahasiswaService mhsService;
    JurusanService jrsService;
    ResponseStatusException stdException;

    @PostConstruct
    public void init() throws Exception {
        this.mhsService = new MahasiswaService();
        this.jrsService = new JurusanService();
        this.stdException = new ResponseStatusException(
            HttpStatus.BAD_GATEWAY,
            "Something wrong happened"
        );
    }

    @GetMapping("/view/mahasiswa/{id}")
    String mahasiswa(Model model, @PathVariable("id") Integer id, HttpServletRequest request) {
        try {
            LoginController.authenticate(request.getCookies());
            Mahasiswa mhs = this.mhsService.getById(id);
            List<Jurusan> jrs = this.jrsService.getList();
            model.addAttribute("mahasiswa", mhs);
            model.addAttribute("list_jurusan", jrs);
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
    String listMahasiswa(Model model, @RequestParam Integer page, HttpServletRequest request) {
        try {
            LoginController.authenticate(request.getCookies());
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
    String addMahasiswa(Model model, HttpServletRequest request) {
        try {
            LoginController.authenticate(request.getCookies());
            Mahasiswa newMhs = new Mahasiswa();
            List<Jurusan> jrs = this.jrsService.getList();
            model.addAttribute("mahasiswa", newMhs);
            model.addAttribute("list_jurusan", jrs);
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