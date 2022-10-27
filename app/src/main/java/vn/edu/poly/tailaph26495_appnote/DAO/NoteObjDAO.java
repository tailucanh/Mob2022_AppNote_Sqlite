package vn.edu.poly.tailaph26495_appnote.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import java.util.ArrayList;

import vn.edu.poly.tailaph26495_appnote.DATABASE.MySqlDatabase;
import vn.edu.poly.tailaph26495_appnote.DTO.NoteSubject;

public class NoteObjDAO {
    SQLiteDatabase sqLiteDatabase;
     MySqlDatabase mySqlDatabase;

     public  NoteObjDAO(Context context){
         mySqlDatabase = new MySqlDatabase(context);
         sqLiteDatabase = mySqlDatabase.getWritableDatabase();
     }

     public void close(){
         mySqlDatabase.close();
     }

    public long insertNotes(NoteSubject noteSubject){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", noteSubject.getTitle());
        contentValues.put("content", noteSubject.getContent());
        long res = sqLiteDatabase.insert("tb_noteObj", null, contentValues);
        return  res;
    }

    public int deleteNote(NoteSubject noteSubject){
        int res = sqLiteDatabase.delete("tb_noteObj","id = ?", new String[]{noteSubject.getId() +""});
        return  res;
    }


    public ArrayList<NoteSubject> selectAll(){
        ArrayList<NoteSubject> lists = new ArrayList<>();
        String sql_Select = "SELECT * FROM tb_noteObj";
        Cursor cursor = sqLiteDatabase.rawQuery(sql_Select,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                NoteSubject noteSubject = new NoteSubject();
                noteSubject.setId(cursor.getInt(0));
                noteSubject.setTitle(cursor.getString(1));
                noteSubject.setContent(cursor.getString(2));
                lists.add(noteSubject);

                cursor.moveToNext(); ///Phải cho vào while
            }
        }

        return lists;
    }



}
