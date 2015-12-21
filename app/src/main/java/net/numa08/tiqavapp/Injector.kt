package net.numa08.tiqavapp

import kotlin.reflect.KProperty

public interface Injector<T> {
    operator fun getValue(thisRef: Any, prop: KProperty<*>): T
}