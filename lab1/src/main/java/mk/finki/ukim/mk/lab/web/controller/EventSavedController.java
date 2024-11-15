package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.LocationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events/add")
public class EventSavedController {
    @PostMapping
    public String saveEvent(@RequestParam String editName,@RequestParam String editDes,@RequestParam String editRate,@RequestParam String editLocation){
        Event newEvent=new Event(editName,editDes,Double.parseDouble(editRate));
        newEvent.setId();
        LocationRepository tmp=new LocationRepository();
        newEvent.setLocation(tmp.findByID(Long.parseLong(editLocation)));
        EventServiceImpl obj=new EventServiceImpl();
        obj.addEvent(newEvent);
        return "redirect:/events";
    }
    @GetMapping
    public String getHtml(Model model){
        LocationServiceImpl locations=new LocationServiceImpl();
        model.addAttribute("locations",locations.findAll());
        return "add-event";
    }


}
