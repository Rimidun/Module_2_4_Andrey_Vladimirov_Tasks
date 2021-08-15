package project.service.implementation;


import project.entity.File;
import project.repository.FileRepository;
import project.repository.implementation.FileRepositoryImplementation;
import project.service.FileService;

import java.util.List;

public class FileServiceImplementation implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImplementation() {
        this.fileRepository = new FileRepositoryImplementation();
    }

    @Override
    public File save(File file) {
        if(file.getId() == null){
            return fileRepository.save(file);
        }else {
            return fileRepository.update(file);
        }
    }

    @Override
    public File update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public File get(Long id) {
        return fileRepository.get(id);
    }

    @Override
    public void remove(File file) {
        fileRepository.remove(file);
    }

    @Override
    public List<File> getAll(Long userId) {
        return fileRepository.getAll(userId);
    }
}
