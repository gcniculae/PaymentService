package com.payment.converter;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<E, D> {

    public abstract D convertEntityToDto(E entity);

    public abstract E convertDtoToEntity(D dto);

    public List<D> convertEntityListToDtoList(List<E> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }

        return entityList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<E> convertDtoListToEntityList(List<D> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
        }

        return dtoList.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }
}
