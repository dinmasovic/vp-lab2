package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;

public interface EventService {
        public List<Event> listAll();
        public List<Event> searchEvents(String text);
        public Event findById(Long id);
}
