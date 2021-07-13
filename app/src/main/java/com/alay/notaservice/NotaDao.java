package com.alay.notaservice;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotaDao {
    @Query("SELECT * FROM nota")
    List<Nota> getAll();

    @Query("INSERT INTO nota (nama,nomer_hp,jenis_barang,kerusakan,nama_teknisi) VALUES (:name, :hp, :jb, :kerusakan, :teknisi)")
    void insertAll(String name, String hp, String jb, String kerusakan, String teknisi);

    @Query("UPDATE nota SET nama=:name, nomer_hp=:hp, jenis_barang=:jb, kerusakan=:kerusakan, nama_teknisi=:teknisi WHERE nid=:nid")
    void update(int nid, String name, String hp, String jb, String kerusakan, String teknisi);

    @Query("SELECT * FROM nota WHERE nid=:nid")
    Nota get(int nid);

    @Delete
    void delete(Nota nota);
}
