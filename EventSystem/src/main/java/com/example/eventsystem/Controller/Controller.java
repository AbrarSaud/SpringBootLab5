package com.example.eventsystem.Controller;


import com.example.eventsystem.Api.ApiResponse;
import com.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class Controller {
    ArrayList<Event> events = new ArrayList<>();

    //    Display all event
    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

    //    Create a new event
    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("added successfully!!");
    }

    // Update a event
    @PutMapping("/update/{id}")
    public ApiResponse updateEvent(@PathVariable int id, @RequestBody Event event) {

        for (Event e : events) {
            if (e.getId() == id) {
                e.setDescription(event.getDescription());
                e.setCapacity(event.getCapacity());
                e.setStartDate(event.getStartDate());
                e.setEndDate(event.getEndDate());
                return new ApiResponse("Updated successfully");
            }
        }
        return new ApiResponse("Event not found!");
    }

    //Delete a event
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable int id) {

        for (Event e : events) {
            if (e.getId() == id) {
                events.remove(e);
                return new ApiResponse("Deleted successfully");
            }
        }
        return new ApiResponse("Event not found!");
    }

    // Change capacity
    @PutMapping("/change/{id}")
    public ApiResponse changeCapacity(@PathVariable int id, @RequestParam int capacity) {
        for (Event e : events) {
            if (e.getId() == id) {
                e.setCapacity(capacity);
                return new ApiResponse("Change capacity successfully to " + capacity);
            }
        }
        return new ApiResponse("Event not found!");
    }


    // Search for a event by given id
    @GetMapping("/getById/{id}")
    public ArrayList<Event> searchById(@PathVariable int id) {
        for (Event e : events) {
            if (e.getId() == id) {
                return events;
            }
        }
        return null;
    }
}
