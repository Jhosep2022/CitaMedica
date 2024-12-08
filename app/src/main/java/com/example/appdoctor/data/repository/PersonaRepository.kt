package com.example.appdoctor.data.repository

import com.example.appdoctor.data.api.PersonaApi
import com.example.appdoctor.data.model.Doctor
import com.example.appdoctor.data.model.Paciente
import com.example.appdoctor.data.model.Persona
import com.example.appdoctor.utils.RetrofitClient

class PersonaRepository {
    private val api = RetrofitClient.instance.create(PersonaApi::class.java)

    suspend fun login(usuario: String, clave: String) =
        api.login(mapOf("usuario" to usuario, "clave" to clave))

    suspend fun registrarPersona(persona: Persona) = api.registrarPersona(persona)
    suspend fun registrarPaciente(paciente: Paciente) = api.registrarPaciente(paciente)
    suspend fun registrarDoctor(doctor: Doctor) = api.registrarDoctor(doctor)
    suspend fun obtenerTodos() = api.obtenerTodos()
    suspend fun obtenerPorId(id: Long) = api.obtenerPorId(id)
    suspend fun actualizarPersona(id: Long, persona: Persona) = api.actualizarPersona(id, persona)
    suspend fun eliminarPersona(id: Long) = api.eliminarPersona(id)
    suspend fun getUbigeos() = api.getUbigeos()
    suspend fun obtenerDetallesPacientes() = api.obtenerDetallesPacientes()
    suspend fun obtenerDetallesDoctores() = api.obtenerDetallesDoctores()
    suspend fun obtenerDetalleDoctor(id: String) = api.obtenerDetalleDoctor(id)


}
