package project.repository;

import project.entity.Event;

import java.util.List;

public interface EventRepository extends GenericRepository<Event, Long> {

    List<Event> getAll(Long userId);
}

