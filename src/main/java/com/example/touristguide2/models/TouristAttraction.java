package com.example.touristguide2.models;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String city;
    private List<String> tags;


    //constructor
    public TouristAttraction(String name, String description, String city, List<String> tags){
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;

    }

    public TouristAttraction() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity()
    {return city;}

    public void setCity(String city)
    {this.city = city;}

    public List<String> getTags()
    {return tags;}

    public void setTags(List<String> tags)
    {this.tags = tags;}
}
