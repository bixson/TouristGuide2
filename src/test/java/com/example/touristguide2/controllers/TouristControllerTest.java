package com.example.touristguide2.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import controllers.TouristController;
import models.TouristAttraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import repositories.TouristRepository;
import services.TouristService;
import repositories.TagList;

import java.util.List;
import java.util.ArrayList;


@WebMvcTest(TouristController.class)
//@SpringBootTest
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private TouristService touristService;

    private TouristAttraction touristAttraction;
    private TouristRepository touristRepository;
    private List<TouristAttraction> attractions;
    private List<TouristAttraction> mockAttractions;


    @BeforeEach
    void setUp() {
        attractions = List.of(
                new TouristAttraction("Den Lille Havfrue", "Den er meget lille", "København", List.of("Udendørs", "Kunst")),
                new TouristAttraction("Amalienborg", "Der bor kongen", "København", List.of("Historisk", "Museum"))
        );
        TouristAttraction touristAttraction1 = new TouristAttraction("touristAttraction 1", "description1", "city1", List.of("Natteliv", "Studieliv"));
        TouristAttraction touristAttraction2 = new TouristAttraction("touristAttraction 2", "description2", "city1", List.of("Natteliv", "Studieliv"));
        TouristAttraction touristAttraction3 = new TouristAttraction("touristAttraction 3", "description3", "city2", List.of("Natteliv", "Studieliv"));
        mockAttractions = List.of(touristAttraction1, touristAttraction2, touristAttraction3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showAllTouristAttractions() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/attractions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(model().attributeExists("attractions", attractions))
                .andExpect(MockMvcResultMatchers.view().name("attraction-list"));

        Mockito.verify(touristService, Mockito.times(1)).getAllTouristAttractions();
    }

    @Test
    void showAddAttractionForm() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(model().attributeExists("attractions", attractions))
                .andExpect(MockMvcResultMatchers.view().name("add-attraction"));

        Mockito.verify(touristService, Mockito.times(1)).getAllCities();
    }

    @Test
    void testSaveTouristAttraction() throws Exception {
        // Mock the service to return the mockAttractions
        when(touristService.getAllTouristAttractions()).thenReturn(mockAttractions);

        // Creates a new tourist attraction to save
        TouristAttraction attractionToSave = new TouristAttraction(
                "New Attraction",
                "A new description",
                "New City",
                List.of(TagList.Historisk.name(), TagList.Museum.name()) // Using enum values for tags
        );

        // Simulates adding the new attraction to the list (without modifying mockAttractions)
        List<TouristAttraction> updatedAttractions = new ArrayList<>(mockAttractions);
        updatedAttractions.add(attractionToSave);

        // Mocks the service to return the updated list after saving the new attraction
        when(touristService.getAllTouristAttractions()).thenReturn(updatedAttractions);

        // Performs the post request to save the new attraction
        mockMvc.perform(post("/save")
                        .param("name", attractionToSave.getName())
                        .param("description", attractionToSave.getDescription())
                        .param("city", attractionToSave.getCity())
                        .param("tags", TagList.Historisk.name()) // Passing enum values for tags
                        .param("tags", TagList.Museum.name())   // Second valid tag
                )
                .andExpect(status().is3xxRedirection()) // Expect a redirect after save
                .andExpect(redirectedUrl("/attractions")); // Expect a redirect to /attractions after save

        // Verifies that the service method was called once to add a new tourist attraction
        verify(touristService, times(1)).addTouristAttraction(any(TouristAttraction.class));
    }

    @Test
    void showAttractionTags() throws Exception {
        String attractionName = "Den Lille Havfrue";
        TouristAttraction attraction = new TouristAttraction(attractionName, "En lille statue", "København", List.of("Udendørs", "Kunst"));
        when(touristService.getTouristAttraction(attractionName)).thenReturn(attraction);
        mockMvc.perform(get("/" + attractionName + "/tags"))
            .andExpect(status().isOk())
            .andExpect(view().name("tags"))
            .andExpect(model().attributeExists("attraction"))
            .andExpect(model().attributeExists("tags"))
            .andExpect(model().attribute("tags", attraction.getTags()));
        verify(touristService, times(1)).getTouristAttraction(attractionName);
    }

    @Test
    void showEditForm() throws Exception {
        String attractionName = "Den Lille Havfrue";
        TouristAttraction attraction = new TouristAttraction(attractionName, "En lille statue", "København", List.of("Udendørs", "Kunst"));

        when(touristService.getTouristAttraction(attractionName)).thenReturn(attraction);
        when(touristService.getAllCities()).thenReturn(List.of("København", "Aarhus"));
        when(touristService.getAllTags()).thenReturn(List.of("Udendørs", "Kunst"));

        mockMvc.perform(get("/attractions/" + attractionName + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("update-attraction"))
                .andExpect(model().attributeExists("touristAttraction"))
                .andExpect(model().attributeExists("cities"))
                .andExpect(model().attributeExists("allTags"));

        verify(touristService, times(1)).getTouristAttraction(attractionName);
        verify(touristService, times(1)).getAllCities();
        verify(touristService, times(1)).getAllTags();
    }

    @Test
    void updateTouristAttraction() throws Exception {
        String attractionName = "Den Lille Havfrue";
        TouristAttraction updatedAttraction = new TouristAttraction(
                attractionName, "Opdateret beskrivelse", "København", List.of("Udendørs", "Historisk")
        );

        // Mocks service to avoid NullPointerException
        doNothing().when(touristService).updateTouristAttraction(eq(attractionName), any(TouristAttraction.class));

        mockMvc.perform(post("/attractions/{name}/update", attractionName)
                        .param("name", updatedAttraction.getName())
                        .param("description", updatedAttraction.getDescription())
                        .param("city", updatedAttraction.getCity())
                        .param("tags", "Udendørs")
                        .param("tags", "Historisk"))
                .andDo(print()) // Prints request/response in the terminal
                .andExpect(status().is3xxRedirection()) // Expects redirect
                .andExpect(redirectedUrl("/attractions"));

        verify(touristService, times(1)).updateTouristAttraction(eq(attractionName), any(TouristAttraction.class));
    }


    @Test
    void deleteTouristAttraction() throws Exception {
        String attractionName = "Den Lille Havfrue";
        mockMvc.perform(get("/" + attractionName + "/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/attractions"));
        verify(touristService, times(1)).deleteTouristAttraction(attractionName);
    }

    @Test
    void showAdminPage() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin"));
    }

    @Test
    void showSelectAttractionPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/select-attraction"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("select-attraction"));
        Mockito.verify(touristService, Mockito.times(1)).getAllTouristAttractions();
    }

    @Test
    void redirectToEdit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/select-attraction"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("select-attraction"));
        Mockito.verify(touristService, Mockito.times(1)).getAllTouristAttractions();

    }
}