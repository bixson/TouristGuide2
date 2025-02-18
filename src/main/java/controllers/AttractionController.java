package com.example.touristguide2.controllers;

import models.TouristAttraction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS
 *                                  - MIDLERTIDIG KLASSE -
 * OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS OBS
 */



@Controller
public class AttractionController {

    @GetMapping("/attractions")
    public String showAttractionsPage(Model model) {
        List<TouristAttraction> attractions = new ArrayList<>();

        List<String> categories1 = new ArrayList<>();
        categories1.add("Kunst");
        attractions.add(new TouristAttraction("Den LilleHavfrue", "den er lille", "Copenhagen", categories1));

        List<String> categories2 = new ArrayList<>();
        attractions.add(new TouristAttraction("Nyhavn", "Copenhagen", "Description for Nyhavn", categories2));

        List<String> categories3 = new ArrayList<>();
        attractions.add(new TouristAttraction("Tivoli Gardens", "Copenhagen", "Description for Tivoli Gardens", categories3));

        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/addAttraction")
    public String showAddAttractionPage() {
        return "addAttraction";
    }

    @GetMapping("/updateAttraction")
    public String showUpdateAttractionPage() {
        return "updateAttraction";
    }
}