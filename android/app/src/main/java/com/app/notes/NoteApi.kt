package com.app.notes

import com.app.notes.model.Note
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteApi {

    @GET("notes")
    fun getAllNotes(): Call<ApiResponse<List<Note>>>

    @POST("notes")
    fun createNote(
        @Body note: Map<String, String>
    ): Call<ApiResponse<Note>>


    @DELETE("notes/{id}")
    fun deleteNote(
        @Path("id") id: String
    ): Call<ApiResponse<Unit>>



}