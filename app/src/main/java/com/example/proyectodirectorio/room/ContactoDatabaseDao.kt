package com.example.proyectodirectorio.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectodirectorio.models.Contacto
import kotlinx.coroutines.flow.Flow

/**
 * Esta clase maneja los métodos para las operaciones a la base de datos.
 */
@Dao
interface ContactoDatabaseDao {
    @Query("Select * From contactos")
    fun getContactos(): Flow<List<Contacto>>

    @Query("Select * From contactos Where id = :id")
    fun getContactoById(id: Long): Flow<Contacto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contacto: Contacto)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(contacto: Contacto)

    @Delete
    suspend fun delete(contacto: Contacto)
}