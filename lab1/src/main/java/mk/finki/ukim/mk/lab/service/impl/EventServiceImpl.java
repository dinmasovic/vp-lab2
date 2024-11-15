package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventServiceImpl implements EventService {

    @Override
    public List<Event> listAll() {
        EventRepository obj=new EventRepository();
        return obj.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        EventRepository obj=new EventRepository();
        return obj.searchEvents(text);
    }

    @Override
    public Event findById(Long id) {
        EventRepository obj=new EventRepository();
        return obj.findById(id);
    }

    public Location getLocation(long id){
        EventRepository obj=new EventRepository();
        return obj.getLocation(id);
    }
    public void addEvent(Event a){
        EventRepository obj=new EventRepository();
        obj.addEvent(a);
    }
    public void removeEvent(Long id){
        EventRepository obj=new EventRepository();
        obj.removeEvent(id);
    }

}
