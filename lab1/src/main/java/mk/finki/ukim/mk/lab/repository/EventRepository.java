package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {

    private static List<Event> list = new ArrayList<>();
    static LocationRepository tmp=new LocationRepository();
    static List<Location> lokacii=tmp.findAll();

    static {
        if (list.isEmpty()) {
            Event event1 = new Event();
            event1.name = "Music Festival";
            event1.description = "A lively festival featuring multiple artists across various genres.";
            event1.popularityScore = 9.5;
            event1.setId();
            event1.setLocation(lokacii.get(0));

            Event event2 = new Event();
            event2.name = "Art Exhibition";
            event2.description = "A showcase of contemporary artists and their remarkable works.";
            event2.popularityScore = 8.0;
            event2.setId();
            event2.setLocation(lokacii.get(1));

            Event event3 = new Event();
            event3.name = "Tech Conference";
            event3.description = "A gathering of industry leaders discussing the future of technology.";
            event3.popularityScore = 9.0;
            event3.setId();
            event3.setLocation(lokacii.get(2));

            Event event4 = new Event();
            event4.name = "Food Truck Festival";
            event4.description = "A culinary adventure featuring local food trucks and delicious cuisines.";
            event4.popularityScore = 8.5;
            event4.setId();
            event4.setLocation(lokacii.get(3));

            Event event5 = new Event();
            event5.name = "Charity Run";
            event5.description = "A fun run to raise money for a local charity, open to all ages.";
            event5.popularityScore = 7.5;
            event5.setId();
            event5.setLocation(lokacii.get(4));

            // Add all events to the static list
            list.add(event1);
            list.add(event2);
            list.add(event3);
            list.add(event4);
            list.add(event5);
        }
    }

    public List<Event> findAll() {
        return list;
    }

    public List<Event> searchEvents(String text) {
        List<Event> containsText = new ArrayList<>();
        for (Event event : list) {
            if (event.name.contains(text) || event.description.contains(text)) {
                containsText.add(event);
            }
        }
        return containsText;
    }
    public Event findById(Long id){
        for(Event i:list){
            if(i.getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    public Location getLocation(Long id){
        for(Event i:list){
            if(i.getId().equals(id)){
                return i.getLocation();
            }
        }
        return null;
    }
    public void addEvent(Event a){
        list.add(a);
    }
    public void removeEvent(Long id){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                list.remove(i);
                break;
            }
        }
    }
    public void updateEvent(Event eventToUpdate) {
        for (int i = 0; i < list.size(); i++) {
            Event existingEvent = list.get(i);
            if (existingEvent.name.equals(eventToUpdate.name)) {
                existingEvent.description = eventToUpdate.description;
                existingEvent.popularityScore = eventToUpdate.popularityScore;
            }
        }
    }
}
