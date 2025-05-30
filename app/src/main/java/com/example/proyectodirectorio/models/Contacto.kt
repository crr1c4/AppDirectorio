package com.example.proyectodirectorio.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa un contacto en la aplicación.
 *
 * Esta clase define la estructura de datos para almacenar información de contactos.
 *
 * @property id Identificador único autogenerado (clave primaria)
 * @property nombre Primer nombre del contacto
 * @property apellido Apellido(s) del contacto
 * @property numero Teléfono del contacto
 * @property correo Dirección de email del contacto
 *
 * @Entity Anotación que marca esta clase como entidad de base de datos
 * @PrimaryKey Indica la clave primaria con generación automática
 * @ColumnInfo Permite personalizar el nombre de columna en la tabla
 */
@Entity(tableName = "contactos")
data class Contacto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "apellido")
    val apellido: String,
    @ColumnInfo(name = "numero")
    val numero: String,
    @ColumnInfo(name = "correo")
    val correo: String,
)
