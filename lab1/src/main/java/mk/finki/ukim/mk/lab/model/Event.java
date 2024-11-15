package mk.finki.ukim.mk.lab.model;
import lombok.Data;
import lombok.Getter;

@Data
public class Event {
    public String name;
    public String description;
    public double popularityScore;
    @Getter
    private Long id;
    private Location location;
    public Event(String name,String description,double popularityScore){
        this.description=description;
        this.name=name;
        this.popularityScore=popularityScore;
    }
    public Event(){}
    public void setLocation(Location o){
        location=o;
    }
    public void setId(){
        id = (long) (Math.random() * 1000);
    }
    public Location getLocation(){
        return location;
    }

}
