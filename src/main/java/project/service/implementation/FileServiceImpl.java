package project.service.implementation;


import project.entity.File;
import project.repository.FileRepository;
import project.repository.implementation.FileRepositoryImpl;
import project.service.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImpl() {
        this.fileRepository = new FileRepositoryImpl();
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
