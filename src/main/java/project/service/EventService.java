package project.service;


import project.entity.Event;

import java.util.List;

public interface EventService extends GenericService<Event, Long>{
    List<Event> getAll(Long userId);
}
