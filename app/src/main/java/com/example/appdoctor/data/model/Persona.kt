package com.example.appdoctor.data.model

// Clase base abierta (no es data class)
open class Persona(
    val id: Long?,
    var nombres: String,
    var apellidos: String,
    var dni: String,
    var genero: String,
    var direccion: String,
    var telefono: String,
    var email: String,
    var usuario: String,
    var clave: String,
    var rolId: Int,
    var idDepartamento: Int,
    var idProvincia: Int,
    var idDistrito: Int
) {
    fun copy(
        nombres: String = this.nombres,
        apellidos: String = this.apellidos,
        dni: String = this.dni,
        genero: String = this.genero,
        direccion: String = this.direccion,
        usuario: String = this.usuario,
        idDepartamento: Int = this.idDepartamento,
        idProvincia: Int = this.idProvincia,
        idDistrito: Int = this.idDistrito
    ): Persona {
        return Persona(
            id = this.id,
            nombres = nombres,
            apellidos = apellidos,
            dni = dni,
            genero = genero,
            direccion = direccion,
            telefono = this.telefono,
            email = this.email,
            usuario = usuario,
            clave = this.clave,
            rolId = this.rolId,
            idDepartamento = idDepartamento,
            idProvincia = idProvincia,
            idDistrito = idDistrito
        )
    }
}


// Subclase Paciente que extiende de Persona
class Paciente(
    val fechaNacimiento: String,
    val edad: Int,
    val tipo: String,
    id: Long? = null, // Ahora id es opcional y puede ser null
    nombres: String,
    apellidos: String,
    dni: String,
    genero: String,
    direccion: String,
    telefono: String,
    email: String,
    usuario: String,
    clave: String,
    rolId: Int,
    idDepartamento: Int,
    idProvincia: Int,
    idDistrito: Int
) : Persona(
    id, nombres, apellidos, dni, genero, direccion, telefono, email, usuario, clave, rolId, idDepartamento, idProvincia, idDistrito
) {
    override fun toString(): String {
        return "Paciente(id=$id, nombres='$nombres', apellidos='$apellidos', dni='$dni', genero='$genero', " +
                "direccion='$direccion', telefono='$telefono', email='$email', usuario='$usuario', " +
                "clave='$clave', rolId=$rolId, idDepartamento=$idDepartamento, idProvincia=$idProvincia, " +
                "idDistrito=$idDistrito, fechaNacimiento='$fechaNacimiento', edad=$edad, tipo='$tipo')"
    }
}



// Subclase Doctor que extiende de Persona
class Doctor(
    val idEspecialidad: Int,
    val idConsultorio: Int,
    val codCmp: String,
    val cargo: String,
    val origen: String,
    id: Long?,
    nombres: String,
    apellidos: String,
    dni: String,
    genero: String,
    direccion: String,
    telefono: String,
    email: String,
    usuario: String,
    clave: String,
    rolId: Int,
    idDepartamento: Int,
    idProvincia: Int,
    idDistrito: Int
) : Persona(
    id, nombres, apellidos, dni, genero, direccion, telefono, email, usuario, clave, rolId, idDepartamento, idProvincia, idDistrito
) {
    override fun toString(): String {
        return "Doctor(id=$id, nombres='$nombres', apellidos='$apellidos', dni='$dni', genero='$genero', " +
                "direccion='$direccion', telefono='$telefono', email='$email', usuario='$usuario', " +
                "clave='$clave', rolId=$rolId, idDepartamento=$idDepartamento, idProvincia=$idProvincia, " +
                "idDistrito=$idDistrito, idEspecialidad=$idEspecialidad, idConsultorio=$idConsultorio, " +
                "codCmp='$codCmp', cargo='$cargo', origen='$origen')"
    }
}
