package com.bs23test.socialmediademo.mapper;

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

//    List <E> toEntity(List<D> dtoList);
//
//    List <D> toDto(List<E> entityList);
}
