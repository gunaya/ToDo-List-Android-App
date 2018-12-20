package com.zucc.todolist.DB;

import android.provider.BaseColumns;

/**
 * Created by Gunaya on 12/20/2018.
 */

public class DbTable {
    public static final class MenuEntry implements BaseColumns{
        public static final String TABLE_NAME = "tb_barang";
        public static final String COLUMN_ID = "";
        public static final String COLUMN_NAME = "nama_barang";
        public static final String COLUMN_PHOTO = "foto_barang";
        public static final String COLUMN_EXP = "kadaluarsa";
        public static final String COLUMN_SALE = "harga_jual";
        public static final String COLUMN_CAT = "";
    }

}
