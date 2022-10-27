package vn.edu.poly.tailaph26495_appnote.DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlDatabase extends SQLiteOpenHelper {
    public  static  int DB_VERSION = 1;
    public  static String DB_NAME = "QLNOTE";

    public MySqlDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_note = "CREATE TABLE  tb_noteObj (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,title TEXT NOT NULL ,content TEXT NOT NULL);";
        db.execSQL(db_note);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
