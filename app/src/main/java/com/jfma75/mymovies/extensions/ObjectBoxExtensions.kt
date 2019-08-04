package com.jfma75.mymovies.extensions

import io.objectbox.Box
import io.objectbox.BoxStore
import kotlin.reflect.KClass

/* ObjectBox extensionss */
inline fun <reified T> BoxStore.boxFor(): Box<T> = boxFor(T::class.java)

fun <T : Any> BoxStore.boxFor(clazz: KClass<T>): Box<T> = boxFor(clazz.java)