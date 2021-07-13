package com.alay.notaservice;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nota {
    @PrimaryKey
    public int nid;

    @ColumnInfo(name = "nama")
    public String name;

    @ColumnInfo(name = "nomer_hp")
    public String hp;

    @ColumnInfo(name = "jenis_barang")
    public String jb;

    @ColumnInfo(name = "kerusakan")
    public String kerusakan;

    @ColumnInfo(name = "nama_teknisi")
    public String teknisi;
}
