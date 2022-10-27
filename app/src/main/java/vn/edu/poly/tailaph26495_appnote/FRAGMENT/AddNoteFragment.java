package vn.edu.poly.tailaph26495_appnote.FRAGMENT;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.tailaph26495_appnote.ADAPTER.NoteAdapter;
import vn.edu.poly.tailaph26495_appnote.DAO.NoteObjDAO;
import vn.edu.poly.tailaph26495_appnote.DTO.NoteSubject;
import vn.edu.poly.tailaph26495_appnote.R;


public class AddNoteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       NoteObjDAO objDAO = new NoteObjDAO(getContext());
       ArrayList<NoteSubject> listNote = new ArrayList<>();

        TextInputLayout ed_title = view.findViewById(R.id.text_input_title);
        TextInputLayout ed_content = view.findViewById(R.id.text_input_content);
        Button btn_note = view.findViewById(R.id.btn_note);

        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              
                if (ed_title.getEditText().getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter title !!", Toast.LENGTH_SHORT).show();
                } else if (ed_content.getEditText().getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter content !!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setIcon(R.drawable.ic_save);
                    builder.setTitle("Confirm !!!");
                    builder.setMessage("Confirm save note: "+ed_title.getEditText().getText().toString());
                    builder.setCancelable(false);

                    NoteSubject noteSubject = new NoteSubject();
                    String title = ed_title.getEditText().getText().toString();
                    String content = ed_content.getEditText().getText().toString();

                    noteSubject.setTitle(title);
                    noteSubject.setContent(content);

                    long res = objDAO.insertNotes(noteSubject);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (res > 0) {
                                listNote.clear();
                                listNote.addAll(objDAO.selectAll());
                                Toast.makeText(getContext(), "Successfully added new! ", Toast.LENGTH_SHORT).show();
                                dialog.cancel();

                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(), "Đã hủy !", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();

                }
            }
        });
    }
}