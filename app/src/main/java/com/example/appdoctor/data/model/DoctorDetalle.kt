package com.example.appdoctor.data.model

data class DoctorDetalle(
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val genero: String,
    val especialidad: String,
    val consultorio: String,
    val codCmp: String,
    val cargo: String,
    val origen: String
)

