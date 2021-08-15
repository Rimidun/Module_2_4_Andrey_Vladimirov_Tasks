package project.service;

import javassist.NotFoundException;
import project.DTO.UserData;
import project.entity.Event;
import project.entity.File;
import project.entity.User;

import java.util.List;

public interface MainService {

    File upload(UserData userData);
    User getFullUserInformation(Long id);
    List<File> getFileLists(Long userId);
    void removeFile(Long userId, String fileName) throws NotFoundException;
    void downloadFile(String fileName, String path);
    List<Event> getEventList(Long userId, Long fileId);
}
