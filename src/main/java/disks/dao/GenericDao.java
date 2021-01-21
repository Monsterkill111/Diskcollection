package disks.dao;

import java.util.List;

public interface GenericDao<T extends MyObject> {
    T getObjectById(Integer key);
    T insert(T object);
    void update(T object);
    void delete(T object);
    List<T> selectAll();
}
