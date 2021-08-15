package project.repository;

import project.entity.File;

import java.util.List;

public interface FileRepository extends GenericRepository<File, Long> {
    List<File> getAll(Long id);
}
