package project.repository.implementation;

import org.hibernate.Session;
import org.hibernate.query.Query;
import project.entity.Event;
import project.repository.EventRepository;

import java.util.List;

public class EventRepositoryImplementation extends GenericRepositoryImplementation<Event, Long> implements EventRepository {

    public EventRepositoryImplementation() {
        super(Event.class);
    }

    @Override
    public List<Event> getAll(Long userId) {
        try(Session session = super.getSession()){
            Query query = session.createQuery("FROM Event WHERE user=" + userId);
            return query.list();
        }
    }
}
