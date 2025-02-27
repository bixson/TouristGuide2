package controllers;

import models.TouristAttraction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.TouristService;

import java.util.Comparator;
import java.util.List;

@Controller
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/attractions")
    public String showAllTouristAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllTouristAttractions();
        attractions.sort(Comparator.comparing(TouristAttraction::getName)); // sort attractions by name [more nice @attraction-list.html]
        model.addAttribute("attractions", attractions);
        return "attraction-list";
    }

    @GetMapping("/add")
    public String showAddAttractionForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("cities", touristService.getAllCities());
        model.addAttribute("allTags", touristService.getAllTags());
        return "add-attraction";
    }

    @PostMapping("/attractions/add")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.saveAttraction(touristAttraction);
        return "redirect:/attractions";
    }


    @PostMapping("/save")
    public String saveTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/tags")
    public String showAttractionTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getTouristAttraction(name);
        if (attraction == null) {
            return "redirect:/attractions";
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", attraction.getTags());
        return "tags";
    }

    @GetMapping("/attractions/{name}/edit")
    public String showEditForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getTouristAttraction(name);
        if (attraction == null) {
            return "redirect:/attractions";
        }
        model.addAttribute("touristAttraction", attraction);
        model.addAttribute("cities", touristService.getAllCities());
        model.addAttribute("allTags", touristService.getAllTags());
        return "update-attraction";
    }

    @PostMapping("/attractions/{name}/update")
    public String updateTouristAttraction(@PathVariable String name, @ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateTouristAttraction(name, touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/delete")
    public String deleteTouristAttraction(@PathVariable String name) {
        touristService.deleteTouristAttraction(name);
        return "redirect:/attractions";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/select-attraction")
    public String showSelectAttractionPage(Model model) {
        List<TouristAttraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "select-attraction";
    }

    @SuppressWarnings("SpringMVCViewInspection") //gives the 'MVC resolve' error. That error can go to ****
    @PostMapping("/select-attraction")
    public String redirectToEdit(@RequestParam String selectedAttraction) {
        return "redirect:/attractions/" + selectedAttraction + "/edit";
    }
}