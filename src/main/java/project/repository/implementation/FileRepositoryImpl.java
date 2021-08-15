package project.repository.implementation;

import org.hibernate.Session;
import org.hibernate.query.Query;
import project.entity.File;
import project.repository.FileRepository;

import java.util.List;

public class FileRepositoryImpl extends GenericRepositoryImpl<File, Long> implements FileRepository {

    public FileRepositoryImpl() {
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
