package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.service.LocationService;
import mk.finki.ukim.mk.lab.service.impl.LocationServiceImpl;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest req){
        String keyword=req.getParameter("searched");
        String rating=req.getParameter("rate");
        EventServiceImpl eventService=new EventServiceImpl();
        List<Event> tmp = eventService.listAll();
        List<Event> filteredEvents = new ArrayList<>();
        if (keyword != null && !keyword.equals("") && rating != null && !rating.equals("")) {
            tmp = eventService.searchEvents(keyword);
            for (Event event : tmp) {
                if (event.getPopularityScore() > Double.parseDouble(rating)) {
                    filteredEvents.add(event);
                }
            }
        } else {
            filteredEvents = tmp;
        }
        model.addAttribute("events",filteredEvents);
        LocationServiceImpl locations=new LocationServiceImpl();
        model.addAttribute("a",filteredEvents.size());
        model.addAttribute("locations",locations.findAll());

        return "listEvents";
    }
    @PostMapping
    public String saveEvent(@RequestParam(required = false) String selected, @RequestParam(required = false) String numTickets, HttpSession session, Model model) {

        session.setAttribute("selected", selected);
        session.setAttribute("numTickets", numTickets);

        if (selected != null && numTickets != null && !numTickets.isEmpty()) {
            return "redirect:/eventBooking";
        } else {

            return "redirect:/events";
        }
    }

}