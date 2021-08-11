package project.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private List<File> files = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
}
