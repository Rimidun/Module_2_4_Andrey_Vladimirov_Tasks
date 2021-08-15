package project;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import project.entity.Event;
import project.entity.File;
import project.entity.User;

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
                .setProperty("hibernate.connection.url", "jdbc:mysql://bfa2d2289b2825:ea530833@us-cdbr-east-03.cleardb.com/heroku_6085da32ed74e00?reconnect=true")
                .addAnnotatedClass(Event.class)
                .addAnnotatedClass(File.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    private MainServiceImpl createMainService(){
        return new MainServiceImpl();
    }

    public MainServiceImpl getMainService(){
        MainServiceImpl mainServiceImpl = (MainServiceImpl) beans.get("MainService");
        if(mainServiceImpl == null){
            beans.put("MainService", createMainService());
            mainServiceImpl = (MainServiceImpl) beans.get("MainService");
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
