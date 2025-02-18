package controllers;

import models.TouristAttraction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import services.TouristService;

import java.util.ArrayList;
import java.util.List;

public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }
    @GetMapping("/attractions")
    public String showAllTouristAttractions(Model model){
        List<TouristAttraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String showAttractionTags(@PathVariable String name, Model model){
        TouristAttraction attraction = touristService.getTouristAttraction(name);
        if (attraction == null) {
            return "tags";
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", attraction.getTags());
        return "tags";
    }
    @GetMapping("/add")
    public String showAddAttractionForm(String name, String description, String city, List tags, Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        return "add-attraction";
    }

    @PostMapping("/save")
    public String saveTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return "redirect:/attractionList";
    }

    @GetMapping("/{name}/edit")
    public String showEditForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getTouristAttraction(name);
        if (attraction == null) {
            return "redirect:/attractionList";
        }
        model.addAttribute("touristAttraction", attraction);
        return "update-attraction";
    }

    @PostMapping("/{name}/update")
    public String updateTouristAttraction(@PathVariable String name, @ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateTouristAttraction(name, touristAttraction);
        return "redirect:/attractionList";
    }

    @GetMapping("/{name}/delete")
    public String deleteTouristAttraction(@PathVariable String name) {
        touristService.deleteTouristAttraction(name);
        return "redirect:/attractionList";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

}
