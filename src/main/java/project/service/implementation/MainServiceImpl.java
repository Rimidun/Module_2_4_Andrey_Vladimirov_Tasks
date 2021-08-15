package project.service.implementation;

import javassist.NotFoundException;
import project.service.MainService;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MainServiceImpl implements MainService {
    private final UserService userService;
    private final FileService fileService;
    private final EventService eventService;
    private final S3Service s3Service;

    public MainServiceImpl() {
        this.userService = new UserServiceImpl();
        this.fileService = new FileServiceImpl();
        this.eventService = new EventServiceImpl();
        this.s3Service = new S3ServiceImpl();
    }

    @Override
    public File upload(UserData userData) {
        S3Object result = s3Service.upload(userData.getPath(), userData.getBucket());
        if (result == null) {
            return null;
        }
        String[] fNameType = result.key().split("\\.");
        User user = new User(userData.getFirstName(), userData.getLastName());
        user = userService.save(user);

        File file = new File();
        file.setName(fNameType[0]);
        file.setType(fNameType[1]);
        file.setSize((float) result.size());
        file.setStatus(FileStatus.ACTIVE);
        file.setLastModified(new Date(result.lastModified().toEpochMilli()));

        List<File> files = fileService.getAll(userData.getId());
        File finalFile = file;
        File find = files.stream().filter(f -> f.equals(finalFile)).findFirst().orElse(null);
        if (find == null) {
            file.setUser(user);
            fileService.save(file);
        } else {
            find.setSize((float) result.size());
            find.setStatus(FileStatus.ACTIVE);
            find.setLastModified(new Date(result.lastModified().toEpochMilli()));
            file = find;
            fileService.update(file);
        }

        Event event = new Event();
        event.setUser(user);
        event.setFile(file);
        eventService.save(event);

        return file;
    }

    @Override
    public User getFullUserInformation(Long id) {
        User find = userService.get(id);
        find.setFiles(fileService.getAll(id));
        find.setEvents(eventService.getAll(id));

        return find;
    }

    @Override
    public List<File> getFileLists(Long userId) {
        return fileService.getAll(userId);
    }

    @Override
    public void removeFile(Long userId, String fileName) throws NotFoundException {
        List<File> files = getFileLists(userId);
        File find = files.stream().filter(f -> f.getFileName().equals(fileName)).findFirst().orElse(null);
        if (find == null) {
            throw new NotFoundException("File is " + fileName + " not found");
        }

        s3Service.remove(fileName, "module24");
        find.setStatus(FileStatus.DELETED);

        fileService.update(find);
        Event event = new Event();
        User user = userService.load(userId);
        event.setUser(user);
        event.setFile(find);
        eventService.save(event);
    }

    @Override
    public void downloadFile(String fileName, String path) {
        try {
            s3Service.download("module24", fileName, path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Event> getEventList(Long userId, Long fileId) {
        return eventService.getAll(userId).stream()
                .filter(e -> e.getFile().getId().equals(fileId))
                .collect(Collectors.toList());
    }
}
