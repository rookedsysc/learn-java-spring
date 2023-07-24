package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;
import com.example.memorydb.user.model.UserEntity;

import java.util.*;
import java.util.stream.Collectors;

public abstract class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {
    private ArrayList<T> dataList = new ArrayList<T>();
    private long index = 0;

    private Comparator<T> sort = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    @Override
    public T save(T data) {
        if (Objects.isNull(data)) {
            throw new RuntimeException("Data is Null");
        }

        Optional<T> prevData = dataList.stream()
                .filter(d -> d.getId()
                        .equals(data.getId()))
                .findFirst();


        if (prevData.isPresent()) {
            // 기존 데이터가 있는 경우
            dataList.remove(prevData.get());
            dataList.add(data);
        } else {
            index++;
            data.setId(index);
            dataList.add(data);
        }

        return null;
    }

    @Override
    public Optional<T> findByID(ID id) {
        return dataList.stream()
                .filter(d -> d.getId()
                        .equals(id))
                .findFirst();
    }

    @Override
    public List<T> findAll() {
        return dataList.stream()
                .sorted(sort)
                .collect(Collectors.toList());
    }


    @Override
    public void delete(ID id) {
        Optional<T> dataEntity = dataList.stream()
                .filter(d -> d.getId()
                        .equals(id))
                .findFirst();
        dataEntity.ifPresent(t -> dataList.remove(t));
    }
}
