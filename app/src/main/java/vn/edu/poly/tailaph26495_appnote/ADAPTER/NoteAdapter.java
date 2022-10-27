package vn.edu.poly.tailaph26495_appnote.ADAPTER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.tailaph26495_appnote.DAO.NoteObjDAO;
import vn.edu.poly.tailaph26495_appnote.DTO.NoteSubject;
import vn.edu.poly.tailaph26495_appnote.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolderNotes>{
    ArrayList<NoteSubject> listNotes;
    NoteObjDAO noteObjDAO;


    public NoteAdapter(ArrayList<NoteSubject> listNotes, NoteObjDAO noteObjDAO) {
        this.listNotes = listNotes;
        this.noteObjDAO = noteObjDAO;
    }

    public void setFilter(ArrayList<NoteSubject> filterList){
        this.listNotes = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolderNotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  = inflater.inflate(R.layout.layout_one_item_note,null);

        return new NoteAdapter.MyViewHolderNotes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNotes holder, int position) {
        final int index = position;
        NoteSubject noteSubject = listNotes.get(position);

        holder.tv_title.setText(noteSubject.getTitle());
        if(holder.tv_title.getText().toString().length() < 10) {
            holder.layout_item.setBackground(ContextCompat.getDrawable(holder.layout_item.getContext(), R.drawable.custom_solid_one_item));
        }else if(holder.tv_title.getText().toString().length() < 15){
            holder.layout_item.setBackground(ContextCompat.getDrawable(holder.layout_item.getContext(), R.drawable.custom_solid_one_item_2));
        }else{
            holder.layout_item.setBackground(ContextCompat.getDrawable(holder.layout_item.getContext(), R.drawable.custom_solid_one_item_3));
        }

        holder.tv_content.setText(noteSubject.getContent());
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNotes(v.getContext(),noteSubject,index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

        public class MyViewHolderNotes extends  RecyclerView.ViewHolder{
            TextView tv_title,tv_content,tv_delete;
            LinearLayout layout_item ;
            public MyViewHolderNotes(@NonNull View view) {
                super(view);

                layout_item = view.findViewById(R.id.layout_one_item);
                tv_title = view.findViewById(R.id.tv_title_note);
                tv_content = view.findViewById(R.id.tv_content_note);
                tv_delete = view.findViewById(R.id.tv_delete);

            }
        }




    public void deleteNotes(Context context, NoteSubject noteSubject, int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Are you sure to delete? : " + noteSubject.getTitle());
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int kq = noteObjDAO.deleteNote(noteSubject);
                if(kq > 0)
                {
                    listNotes.remove(index);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Deleted! ", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }else
                    Toast.makeText(context, "Can not delete! " + kq, Toast.LENGTH_SHORT).show();

                dialogInterface.dismiss();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Cancelled!", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }



}
