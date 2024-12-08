package com.example.appdoctor.data.model

data class PacienteDetalle(
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val genero: String,
    val fechaNacimiento: String,
    val edad: Int,
    val tipo: String
)
