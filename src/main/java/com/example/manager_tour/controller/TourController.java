package com.example.manager_tour.controller;


import com.example.manager_tour.model.Tour;
import com.example.manager_tour.model.TourForm;
import com.example.manager_tour.service.HibernateTourService;
import com.example.manager_tour.service.IHibernateTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/tour")
@PropertySource("classpath:upload_file.properties")
public class TourController {
    @Value("${upload}")
    private String upload;
    @Autowired
    private IHibernateTourService iHibernateTourService;

    @GetMapping("")
    public String index(Model model) {
        List<Tour> tourList = iHibernateTourService.findAll();
        model.addAttribute("tour", tourList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("tour", new TourForm());
        return "/create";
    }

    @PostMapping("/save")
    public String save(TourForm tourForm) throws IOException {
        // tải file lên
        MultipartFile file = tourForm.getImg();
        String nameImg = file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(upload+nameImg));
        Tour tour = new Tour();
        tour.setCode(tourForm.getCode());
        tour.setDestination(tourForm.getDestination());
        tour.setPrice(tourForm.getPrice());
        tour.setImg(nameImg);
        iHibernateTourService.save(tour);
        return "redirect: /tour";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("tour", iHibernateTourService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(TourForm tourForm) throws IOException {
        MultipartFile file = tourForm.getImg();
        String nameImg = file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(upload+nameImg));
        Tour tour = new Tour();
        tour.setId(tourForm.getId());
        tour.setCode(tourForm.getCode());
        tour.setDestination(tourForm.getDestination());
        tour.setPrice(tourForm.getPrice());
        tour.setImg(nameImg);
        iHibernateTourService.edit(tour);
        return "redirect: /tour";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("tour", iHibernateTourService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Tour tour, RedirectAttributes attributes) {
        iHibernateTourService.remove(tour.getId());
        attributes.addFlashAttribute("success", "Remove tour successfully");
        return "redirect: /tour";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("tour",iHibernateTourService.findById(id));
        return "/view";
    }
}
