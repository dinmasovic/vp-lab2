package mk.finki.ukim.mk.lab.web;
import java.util.ArrayList;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;


@WebServlet(name="EventListServlet",urlPatterns = "")
public class EventListSevlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventServiceImpl eventService;

    public EventListSevlet(EventServiceImpl eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        String keyword=req.getParameter("searched");
        String rating=req.getParameter("rate");
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
            filteredEvents = tmp; // No filtering if no keyword or rating provided
        }

// Set the list of events to the Thymeleaf context
        context.setVariable("events", filteredEvents);

// Process the template
        springTemplateEngine.process("listEvents.html", context, resp.getWriter());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if the radio button was selected
        String selectedEvent = req.getParameter("selected");
        String numTickets = req.getParameter("numTickets");
        HttpSession session = req.getSession();
        session.setAttribute("selected", selectedEvent);
        session.setAttribute("numTickets", numTickets);
        if (selectedEvent != null && numTickets!=null && !numTickets.equals("")) { //
            resp.sendRedirect("/eventBooking");
        }else
            resp.sendRedirect("");
        }

    }


