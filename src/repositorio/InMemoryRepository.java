package repositorio;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRepository<T> {
    protected Map<Long, T> data = new HashMap();
    protected AtomicLong idGenerator = new AtomicLong();

    public InMemoryRepository() {
    }

    public T save(T entity) {
        long id = this.idGenerator.incrementAndGet();

        try {
            entity.getClass().getMethod("setId", Long.class).invoke(entity, id);
            String clase = entity.getClass().getName();
            System.out.println(clase + "   id :" + id);
        } catch (Exception var5) {
            Exception e = var5;
            e.printStackTrace();
        }

        this.data.put(id, entity);
        return entity;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(this.data.get(id));
    }

    public List<T> findAll() {
        return new ArrayList(this.data.values());
    }

    public Optional<T> genericUpdate(Long id, T updatedEntity) {
        if (!this.data.containsKey(id)) {
            return Optional.empty();
        } else {
            try {
                Method setIdMethod = updatedEntity.getClass().getMethod("setId", Long.class);
                setIdMethod.invoke(updatedEntity, id);
                this.data.put(id, updatedEntity);
                return Optional.of(updatedEntity);
            } catch (Exception var4) {
                Exception e = var4;
                e.printStackTrace();
                return Optional.empty();
            }
        }
    }

    public Optional<T> genericDelete(Long id) {
        return !this.data.containsKey(id) ? Optional.empty() : Optional.ofNullable(this.data.remove(id));
    }

    public List<T> genericFindByField(String fieldName, Object value) {
        List<T> results = new ArrayList();

        try {
            Iterator var9 = this.data.values().iterator();

            while(var9.hasNext()) {
                T entity = (T) var9.next();
                Method getFieldMethod = entity.getClass().getMethod("get" + this.capitalize(fieldName));
                Object fieldValue = getFieldMethod.invoke(entity);
                if (fieldValue != null && fieldValue.equals(value)) {
                    results.add(entity);
                }
            }
        } catch (Exception var8) {
            Exception e = var8;
            e.printStackTrace();
        }

        return results;
    }

    private String capitalize(String str) {
        if (str != null && !str.isEmpty()) {
            String var10000 = str.substring(0, 1).toUpperCase();
            return var10000 + str.substring(1);
        } else {
            return str;
        }
    }
}

