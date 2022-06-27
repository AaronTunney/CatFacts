package com.aarontunney.catfacts

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Creates an interface used by Retrofit to call the backend service and convert the JSON into
// native Kotlin objects.
//
// API documentation for Cat Facts: https://alexwohlbruck.github.io/cat-facts/docs/
interface ApiInterface {

    // A HTTP GET call to the endpoint /facts
    @GET("facts")
    // The method that the view model calls. suspend means that it is asynchronous.
    suspend fun getFacts(): List<Fact>

    companion object {
        // The base URL of the RESTful API
        var BASE_URL = "https://cat-fact.herokuapp.com/"

        // Builds the request
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}