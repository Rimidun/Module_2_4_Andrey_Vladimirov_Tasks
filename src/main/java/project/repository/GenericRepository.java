package project.repository;

public interface GenericRepository<T, ID> {

    T save(T t);
    T update(T t);
    T get(ID id);
    void remove(T t);
    T load(ID id);
}
