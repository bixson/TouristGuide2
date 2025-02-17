package models;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String city;
    private List tags;


    //constructor
    public TouristAttraction(String name, String description, String city, List tags){
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;

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

    public List getTags()
    {return tags;}

    public void setTags(List tags)
    {this.tags = tags;}
}
