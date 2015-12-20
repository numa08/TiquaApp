package net.numa08.tiqa4k

import retrofit.http.GET
import rx.Observable

interface TiqavService {

    @GET("/search/newest.json")
    public fun newest() : Observable<Array<Tiqav>>

}
