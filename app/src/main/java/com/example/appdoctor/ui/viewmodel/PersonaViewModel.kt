package com.example.appdoctor.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdoctor.data.model.Doctor
import com.example.appdoctor.data.model.DoctorDetalle
import com.example.appdoctor.data.model.Paciente
import com.example.appdoctor.data.model.PacienteDetalle
import com.example.appdoctor.data.model.Persona
import com.example.appdoctor.data.model.Ubigeo
import com.example.appdoctor.data.repository.PersonaRepository
import kotlinx.coroutines.launch

class PersonaViewModel : ViewModel() {
    private val repository = PersonaRepository()

    var usuarioAutenticado: Persona? by mutableStateOf(null)
        private set
    var pacientes: List<PacienteDetalle> by mutableStateOf(emptyList())
        private set
    var doctores: List<DoctorDetalle> by mutableStateOf(emptyList())
        private set
    var doctorDetalle: DoctorDetalle? by mutableStateOf(null)
        private set

    // Login
    fun login(usuario: String, clave: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response = repository.login(usuario, clave)
            if (response.isSuccessful) {
                usuarioAutenticado = response.body()
                onResult(true)
            } else {
                usuarioAutenticado = null
                onResult(false)
            }
        }
    }

    // Obtener perfil del usuario por ID
    fun obtenerPerfil(id: Long, onResult: (Persona?) -> Unit) {
        viewModelScope.launch {
            val response = repository.obtenerPorId(id)
            if (response.isSuccessful) {
                usuarioAutenticado = response.body()
                onResult(response.body())
            } else {
                onResult(null)
            }
        }
    }

    fun registrarPaciente(paciente: Paciente, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response = repository.registrarPaciente(paciente)
            onResult(response.isSuccessful)
        }
    }

    fun registrarDoctor(
        doctor: Doctor,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.registrarDoctor(doctor)
            onResult(response.isSuccessful)
        }
    }



    // Obtener Todos
    fun obtenerTodos(onResult: (List<Persona>?) -> Unit) {
        viewModelScope.launch {
            val response = repository.obtenerTodos()
            onResult(if (response.isSuccessful) response.body() else null)
        }
    }

    // Actualizar Persona
    fun actualizarPersona(id: Long, persona: Persona, onResult: (Persona?) -> Unit) {
        viewModelScope.launch {
            val response = repository.actualizarPersona(id, persona)
            onResult(if (response.isSuccessful) response.body() else null)
        }
    }

    // Eliminar Persona
    fun eliminarPersona(id: Long, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response = repository.eliminarPersona(id)
            onResult(response.isSuccessful)
        }
    }

    fun fetchUbigeos(onResult: (List<Ubigeo>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getUbigeos()
            if (response.isSuccessful) {
                onResult(response.body() ?: emptyList())
            } else {
                onResult(emptyList())
            }
        }
    }

    fun obtenerDetallesPacientes(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response = repository.obtenerDetallesPacientes()
            if (response.isSuccessful) {
                pacientes = response.body() ?: emptyList()
                onResult(true)
            } else {
                pacientes = emptyList()
                onResult(false)
            }
        }
    }

    fun obtenerDetallesDoctores(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response = repository.obtenerDetallesDoctores()
            if (response.isSuccessful) {
                doctores = response.body() ?: emptyList()
                onResult(true)
            } else {
                doctores = emptyList()
                onResult(false)
            }
        }
    }

    fun obtenerDetalleDoctor(id: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response = repository.obtenerDetalleDoctor(id)
            if (response.isSuccessful) {
                doctorDetalle = response.body()
                onResult(true)
            } else {
                doctorDetalle = null
                onResult(false)
            }
        }
    }
}
