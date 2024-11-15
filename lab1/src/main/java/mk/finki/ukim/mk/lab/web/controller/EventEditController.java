package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.LocationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events/edit/{eventId}")
public class EventEditController {
    @PostMapping
    public String getEventsPage(@PathVariable Long eventId, @RequestParam String editName,
                                @RequestParam String editRate, @RequestParam String editDes,
                                @RequestParam String editLocation,Model model) {

        EventServiceImpl obj=new EventServiceImpl();
        if(editName !=null)
        obj.findById(eventId).name=editName;
        if(editRate!=null)
        obj.findById(eventId).popularityScore=Double.parseDouble(editRate);
        if(editDes!=null)
        obj.findById(eventId).description=editDes;
        LocationServiceImpl lokacija=new LocationServiceImpl();
        obj.findById(eventId).setLocation(lokacija.findByID(Long.parseLong(editLocation)));
        return "redirect:/events";
    }
    @GetMapping
    public String editEvent(@PathVariable Long eventId,Model model){
        LocationServiceImpl locations=new LocationServiceImpl();
        EventServiceImpl obj=new EventServiceImpl();
        model.addAttribute("locations",locations.findAll());
        model.addAttribute("rate",obj.findById(eventId).popularityScore);
        model.addAttribute("des",obj.findById(eventId).description);
        model.addAttribute("name",obj.findById(eventId).name);
        //obj.findById(eventId).name="da";
        return "add-event";
    }
}
