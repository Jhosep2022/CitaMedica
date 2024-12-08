package com.example.appdoctor.data.model

data class Ubigeo(
    val id: Long,
    val idDepartamento: Long,
    val idProvincia: Long,
    val idDistrito: Long,
    val descDepartamento: String,
    val descProvincia: String,
    val descDistrito: String,
    val estado: Int
)
