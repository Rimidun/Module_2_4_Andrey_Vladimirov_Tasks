package project.service;


import project.entity.File;

import java.util.List;

public interface FileService extends GenericService<File, Long> {
    List<File> getAll(Long userId);
}
