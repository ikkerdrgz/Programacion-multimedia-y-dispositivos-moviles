package com.example.appbasededatos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AyudanteBaseDatos (context: Context)
    : SQLiteOpenHelper  (context, NOMBRE_BASE_DATOS,
    null, VERSION_BASE_DATOS){
    companion object{
        private const val NOMBRE_BASE_DATOS = "empresa.db"
        private const val VERSION_BASE_DATOS = 2
        const val TABLA_VERSION = "usuarios"
        const val COLUMNA_ID = "id"
        const val COLUMNA_NOMBRE = "nombre"
        const val COLUMNA_CORREO = "correo"
        const val COLUMNA_EDAD = "edad"
        const val COLUMNA_TELEFONO = "telefono"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val crearTabla = """
            CREATE TABLE $TABLA_VERSION(
                $COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMNA_NOMBRE TEXT NOT NULL,
                $COLUMNA_CORREO TEXT NOT NULL,
                $COLUMNA_EDAD INTEGER DEFAULT 0,
                $COLUMNA_TELEFONO TEXT DEFAULT '')
            """
        db.execSQL(crearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE $TABLA_VERSION ADD COLUMN $COLUMNA_EDAD INTEGER DEFAULT 0")
            db.execSQL("ALTER TABLE $TABLA_VERSION ADD COLUMN $COLUMNA_TELEFONO TEXT DEFAULT ''")
        }
    }
}