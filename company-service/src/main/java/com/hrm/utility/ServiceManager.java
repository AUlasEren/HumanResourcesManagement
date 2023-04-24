package com.hrm.utility;

import com.hrm.repository.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Getter
public class ServiceManager <T extends BaseEntity,ID> implements IService<T,ID> {

    private final JpaRepository<T,ID> repository;
    @Override
    public T save(T t) {
        long time = System.currentTimeMillis();
        t.setCreateDate(time);
        t.setUpdateDate(time);
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(x->{
            x.setCreateDate(System.currentTimeMillis());
            x.setUpdateDate(System.currentTimeMillis());
        });
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {
        long time = System.currentTimeMillis();
        t.setUpdateDate(time);
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

}
