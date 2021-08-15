package project.repository.implementation;

import org.hibernate.Session;
import org.hibernate.query.Query;
import project.entity.File;
import project.repository.FileRepository;

import java.util.List;

public class FileRepositoryImplementation extends GenericRepositoryImplementation<File, Long> implements FileRepository {

    public FileRepositoryImplementation() {
        super(File.class);
    }

    @Override
    public List<File> getAll(Long id) {
        try(Session session = super.getSession()){
            Query query = session.createQuery("FROM File WHERE user=" + id);
            return query.list();
        }
    }
}
