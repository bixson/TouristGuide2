package services;

import models.TouristAttraction;
import org.springframework.stereotype.Service;
import repositories.TouristRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(){
        this.touristRepository = new TouristRepository();
    }
    public List<TouristAttraction> getAllTouristAttractions() {
        return touristRepository.getAllTouristAttractions();
    }

    public TouristAttraction getTouristAttraction(String name) {
        Optional<TouristAttraction> attraction = touristRepository.getAllTouristAttractions().stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst();
        return attraction.orElse(null);
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristRepository.addTouristAttraction(touristAttraction);
    }

    public void updateTouristAttraction(String name, TouristAttraction touristAttraction) {
        touristRepository.updateTouristAttraction(name, touristAttraction);
    }

    public void deleteTouristAttraction(String name) {
        touristRepository.deleteTouristAttraction(name);
    }

    public List<String> getAllCities() {
        return touristRepository.getAllCities();
    }

    public List<String> getAllTags() {
        return touristRepository.getAllTags();
    }

    public Optional<TouristAttraction> getTouristAttractionByName(String name) {
        return touristRepository.getAllTouristAttractions().stream()
                .filter(attraction -> attraction.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    //added error-handling for saving tourist attraction [Name, Description & City are REQUIRED [for @add-attraction + @update-attraction]]
    public void saveAttraction(TouristAttraction touristAttraction) {
        //touristRepository.addTouristAttraction(touristAttraction);
        if(touristAttraction == null) {
        throw new IllegalArgumentException("Tourist attraction cannot be null");
        }
        if(touristAttraction.getName() == null || touristAttraction.getName().isEmpty()) {
            throw new IllegalArgumentException("Tourist attraction name cannot be null or empty");
        }
        if(touristAttraction.getCity() == null || touristAttraction.getCity().isEmpty()) {
            throw new IllegalArgumentException("Tourist attraction city cannot be null or empty");
        }
        if(touristAttraction.getDescription() == null || touristAttraction.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Tourist attraction description cannot be null or empty");
        }
        try {
            touristRepository.addTouristAttraction(touristAttraction);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving tourist attraction", e);
        }
    }
}
