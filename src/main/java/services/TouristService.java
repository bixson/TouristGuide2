package services;

import models.TouristAttraction;
import repositories.TouristRepository;

import java.util.List;

public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(){
        this.touristRepository = new TouristRepository();
    }
    public List<TouristAttraction> getAllTouristAttractions() {
        return touristRepository.getAllTouristAttractions();
    }

    public TouristAttraction getTouristAttraction(String name) {
        return touristRepository.getTouristAttraction(name);
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
}
