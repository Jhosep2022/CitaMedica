package com.example.appdoctor.data.api

import com.example.appdoctor.data.model.Doctor
import com.example.appdoctor.data.model.DoctorDetalle
import com.example.appdoctor.data.model.Paciente
import com.example.appdoctor.data.model.PacienteDetalle
import com.example.appdoctor.data.model.Persona
import com.example.appdoctor.data.model.Ubigeo
import retrofit2.Response
import retrofit2.http.*

interface PersonaApi {

    @POST("personas/login")
    suspend fun login(@Body loginRequest: Map<String, String>): Response<Persona>

    @POST("personas/registrar-persona")
    suspend fun registrarPersona(@Body persona: Persona): Response<Persona>

    @POST("personas/registrar-paciente")
    suspend fun registrarPaciente(@Body paciente: Paciente): Response<Paciente>

    @POST("personas/registrar-doctor")
    suspend fun registrarDoctor(@Body doctor: Doctor): Response<Doctor>

    @GET("personas")
    suspend fun obtenerTodos(): Response<List<Persona>>

    @GET("personas/{id}")
    suspend fun obtenerPorId(@Path("id") id: Long): Response<Persona>

    @PUT("personas/{id}")
    suspend fun actualizarPersona(@Path("id") id: Long, @Body persona: Persona): Response<Persona>

    @DELETE("personas/{id}")
    suspend fun eliminarPersona(@Path("id") id: Long): Response<Void>

    @GET("ubigeos")
    suspend fun getUbigeos(): Response<List<Ubigeo>>

    @GET("pacientes/detalles")
    suspend fun obtenerDetallesPacientes(): Response<List<PacienteDetalle>>

    @GET("doctores/detalles")
    suspend fun obtenerDetallesDoctores(): Response<List<DoctorDetalle>>

    @GET("doctores/detalles/{id}")
    suspend fun obtenerDetalleDoctor(@Path("id") id: String): Response<DoctorDetalle>
    
}
