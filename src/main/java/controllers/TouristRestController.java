package controllers;

import models.TouristAttraction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.TouristService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attractions")
public class TouristRestController {

    private final TouristService touristService;

    public TouristRestController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public List<TouristAttraction> getAllAttractions() {
        return touristService.getAllTouristAttractions();
    }

    @GetMapping("/{name}")
    public Optional<TouristAttraction> getAttractionByName(@PathVariable String name) {
        return touristService.getTouristAttractionByName(name);
    }
}

/*
   This class is to return json response for API endpoints
   @index.html without fucking up the HTML views
  **/