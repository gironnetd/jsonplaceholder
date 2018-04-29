package com.vlabs.jsonplaceholder.remote.mappers

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <M> the remote model input type
 * @param <E> the entity model output type
 */
interface EntityMapper<in M, out E> {

    fun mapFromRemote(type: M): E

    fun mapFromRemote(type: List<M>): List<E>


}