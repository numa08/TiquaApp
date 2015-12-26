package net.numa08.tiqavapp.utils

import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqa4k.TiqavGson
import net.numa08.tiqavapp.ApplicationComponent
import net.numa08.tiqavapp.TestApplication
import java.util.*

public class TestComponentHelper {
    val componentStack = LinkedList<ApplicationComponent>()

    public fun pushComponent(app: TestApplication, component: ApplicationComponent) {
        componentStack.push(component)
        app.component = component
    }

    public fun pushComponent(app: TestApplication) {
        app.component = componentStack.pop()
    }

    public fun clear() {
        componentStack.clear()
    }
}

fun loadJSON(json: String) : Array<Tiqav> {
    return TiqavGson.GSON.fromJson(json, Array<Tiqav>::class.java)
}