package repositories;

import models.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> touristAttractionList = new ArrayList<>();

    // Constructor to add initial TouristAttraction objects
    public TouristRepository() {
        touristAttractionList.add(new TouristAttraction("Den Lille Havfrue", "Den er meget lille", "København", List.of("Udendørs", "Kunst")));
        touristAttractionList.add(new TouristAttraction("Amalienborg", "Der bor kongen", "København", List.of("Historisk", "Museum")));
        touristAttractionList.add(new TouristAttraction("Kongens Nytorv", "Der er hyggeligt om sommeren", "København", List.of("Udendørs", "Shopping")));
        touristAttractionList.add(new TouristAttraction("KEA", "Det er en fin skole", "København", List.of("Natteliv", "Studieliv")));
        touristAttractionList.add(new TouristAttraction("Guldbar", "Der er billig øl", "København", List.of("Natteliv", "Studieliv")));
        touristAttractionList.add(new TouristAttraction("Christiania", "Der er frit", "København", List.of("Udendørs")));
        touristAttractionList.add(new TouristAttraction("Nyhavn", "Der er flot og livligt", "København", List.of("Udendørs")));
        touristAttractionList.add(new TouristAttraction("Zoologisk Have", "Der er mange forskellige dyr", "Frederiksberg", List.of("Udendørs", "Børnevenligt")));
        touristAttractionList.add(new TouristAttraction("Amager Centret", "Det er hér det sker", "Amagerbro", List.of("Shopping")));
        touristAttractionList.add(new TouristAttraction("Torvehallerne", "Dyr og lækker madoplevelse","København", List.of("Shopping", "Mad")));
        touristAttractionList.add(new TouristAttraction("H.C. Andersens Hus", "Museum om HC Andersen", "Odense", List.of("Museum", "Historisk", "Børnevenligt")));
        touristAttractionList.add(new TouristAttraction("Kunstmuseum Brandts", "Både klassisk og nutidskunst", "Odense", List.of("Museum", "Kunst")));
        touristAttractionList.add(new TouristAttraction("Den Fynske Landsby", "Oplev en landsby fra 1800-tallet", "Odense", List.of("Historisk", "Udendørs", "Børnevenlig")));
        touristAttractionList.add(new TouristAttraction("Kunsten Museum of Modern Art Aalborg", "Museum for moderne kunst", "Aalborg", List.of("Museum", "Kunst")));
        touristAttractionList.add(new TouristAttraction("Aalborg Havnefront", "Byliv og havluft", "Aalborg", List.of("Udendørs", "Mad", "Shopping")));
        touristAttractionList.add(new TouristAttraction("Jomfru Ane Gade", "I daglig tale kaldt 'Gaden'", "Aalborg", List.of("Natteliv")));
        touristAttractionList.add(new TouristAttraction("Den Gamle By", "Oplev en by gennem historien", "Aarhus", List.of("Historisk", "Udendørs", "Børnevenligt")));
        touristAttractionList.add(new TouristAttraction("ARoS Aarhus Kunstmuseum", "Regnbuepanorama over Århus", "Aarhus", List.of("Kunst", "Museum")));
        touristAttractionList.add(new TouristAttraction("Moesgaard Museum", "Oplev menneskets udvikling", "Aarhus", List.of("Museum", "Historisk", "Børnevenligt")));
        touristAttractionList.add(new TouristAttraction("Randers Regnskov", "Oplev dyre- og plantelivet i de 3 kupler", "Randers", List.of("Børnevenligt")));

    }

    // Read list method
    public List<TouristAttraction> getAllTouristAttractions() {
        return new ArrayList<>(touristAttractionList);
    }


    // Get specific attraction
    public TouristAttraction getTouristAttraction(String name) {
        for (TouristAttraction attraction : touristAttractionList) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }





    // Add/create to list method
    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristAttractionList.add(touristAttraction);
    }



    // Update specific attraction
    public void updateTouristAttraction(String name, TouristAttraction touristAttraction) {
        for (int i = 0; i < touristAttractionList.size(); i++) {
            if (touristAttractionList.get(i).getName().equals(name)) {
                touristAttractionList.set(i, touristAttraction);
                return;
            }
        }
    }

    // Delete from list method
    public void deleteTouristAttraction(String name) {
        touristAttractionList.removeIf(attraction -> attraction.getName().equals(name));
    }

    public List<String> getAllCities() {
        return List.of("København", "Frederiksberg", "Amagerbro", "Odense", "Aalborg", "Aarhus", "Randers");
    }

    public List<String> getAllTags() {
        return List.of("Udendørs", "Kunst", "Historisk", "Museum", "Natteliv", "Shopping", "Mad", "Børnevenligt");
    }
}
