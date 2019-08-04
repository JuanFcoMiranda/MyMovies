package com.jfma75.mymovies.application

import java.lang.IllegalStateException
import kotlin.reflect.KProperty

/**
 * Created by juanfran on 08/02/2018.
 * Esta clase se utiliza para definir un delegado a una propiedad a la que sólo
 * puede asignársele valor 1 única vez, si se intenta leer su valor y éste no
 * está asignado o se le intenta asignar un valor cuando éste ya ha sido asignado,
 * provoca una excepción.
 */

class NotNullSingleValueVar<T> {
    private var value : T? = null

    operator fun getValue(thisRef: Any?, property : KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    operator fun setValue(thisRef: Any?, property : KProperty<*>, value : T) {
        this.value = if (this.value == null) value else throw IllegalStateException("${property.name} already initialized")
    }
}