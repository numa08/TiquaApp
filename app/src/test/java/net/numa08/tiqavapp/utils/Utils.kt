package net.numa08.tiqavapp.utils

import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqa4k.TiqavGson
import net.numa08.tiqavapp.ApplicationComponent
import net.numa08.tiqavapp.TiqavApplication
import java.util.*

public class TestComponentHelper {
    val componentStack = LinkedList<ApplicationComponent>()

    public fun pushComponent(app: TiqavApplication, component: ApplicationComponent) {
        componentStack.push(component)
        app.applicationComponent = component
    }

    public fun pushComponent(app: TiqavApplication) {
        app.applicationComponent = componentStack.pop()
    }

    public fun clear() {
        componentStack.clear()
    }
}

fun loadJSON(json: String) : Array<Tiqav> {
    return TiqavGson.GSON.fromJson(json, Array<Tiqav>::class.java)
}