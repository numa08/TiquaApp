package net.numa08.tiqa4k.mock

import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqa4k.TiqavService
import rx.Observable

public fun mockNewest(tiqavs: Array<Tiqav>): TiqavService {
    return object :TiqavService{
        override fun newest(): Observable<Array<Tiqav>> {
            return Observable.just(tiqavs)
        }
    }
}