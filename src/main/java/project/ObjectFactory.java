package project;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import project.entity.Event;
import project.entity.File;
import project.entity.User;
import project.service.implementation.MainServiceImplementation;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static ObjectFactory INSTANCE = null;
    private Map<String, Object> beans = new HashMap<>();

    private ObjectFactory() {
    }

    public static ObjectFactory getObjectFactory(){
        if(INSTANCE == null){
            synchronized (ObjectFactory.class){
                if(INSTANCE == null){
                    INSTANCE = new ObjectFactory();
                }
            }
        }

        return INSTANCE;
    }

    private SessionFactory createSessionFactory(){
        System.getenv("DATABASE_URL");
        return new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
//                .setProperty("hibernate.connection.url", System.getenv("HIBERNATE_CONNECTION_URL"))
                .setProperty("hibernate.connection.url", "jdbc:mysql://123")
                .addAnnotatedClass(Event.class)
                .addAnnotatedClass(File.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    private MainServiceImplementation createMainService(){
        return new MainServiceImplementation();
    }

    public MainServiceImplementation getMainService(){
        MainServiceImplementation mainServiceImpl = (MainServiceImplementation) beans.get("MainService");
        if(mainServiceImpl == null){
            beans.put("MainService", createMainService());
            mainServiceImpl = (MainServiceImplementation) beans.get("MainService");
        }

        return mainServiceImpl;
    }

    public SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = (SessionFactory) beans.get("sessionFactory");

        if(sessionFactory == null){
            beans.put("sessionFactory", createSessionFactory());
            sessionFactory = (SessionFactory) beans.get("sessionFactory");
        }

        return sessionFactory;
    }

}
