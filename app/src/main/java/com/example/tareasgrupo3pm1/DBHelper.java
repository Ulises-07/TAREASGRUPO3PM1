package com.example.tareasgrupo3pm1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "personas.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE personas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "apellidos TEXT, " +
                "edad INTEGER, " +
                "correo TEXT, " +
                "direccion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS personas");
        onCreate(db);
    }

    public long insertarPersona(String nombre, String apellidos, int edad, String correo, String direccion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellidos", apellidos);
        values.put("edad", edad);
        values.put("correo", correo);
        values.put("direccion", direccion);
        long res = db.insert("personas", null, values);
        db.close();
        return res;
    }

    public Cursor obtenerPersonas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM personas", null);
    }

    public boolean actualizarPersona(int id, String nombre, String apellidos, int edad, String correo, String direccion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellidos", apellidos);
        values.put("edad", edad);
        values.put("correo", correo);
        values.put("direccion", direccion);

        int rows = db.update("personas", values, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }

    public boolean eliminarPersona(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete("personas", "id=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }
}
