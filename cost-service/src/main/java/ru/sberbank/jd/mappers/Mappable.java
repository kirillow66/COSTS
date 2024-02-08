package ru.sberbank.jd.mappers;

import java.util.List;

/**
 * The interface Mappable.
 *
 * @param <E> the type parameter
 * @param <D> the type parameter
 */
public interface Mappable<E, D> {
    /**
     * To dto d.
     *
     * @param entity the entity
     * @return the d
     */
    D toDto(E entity);

    /**
     * To dto list.
     *
     * @param entity the entity
     * @return the list
     */
    List<D> toDto(List<E> entity);

    /**
     * To entity e.
     *
     * @param dto the dto
     * @return the e
     */
    E toEntity(D dto);

    /**
     * To entity list.
     *
     * @param dtos the dtos
     * @return the list
     */
    List<E> toEntity(List<D> dtos);    
}
