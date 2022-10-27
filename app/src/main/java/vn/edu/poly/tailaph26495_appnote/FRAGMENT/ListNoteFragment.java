package vn.edu.poly.tailaph26495_appnote.FRAGMENT;

import android.app.ActivityManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.tailaph26495_appnote.ADAPTER.NoteAdapter;
import vn.edu.poly.tailaph26495_appnote.DAO.NoteObjDAO;
import vn.edu.poly.tailaph26495_appnote.DTO.NoteSubject;
import vn.edu.poly.tailaph26495_appnote.MainActivity;
import vn.edu.poly.tailaph26495_appnote.R;


public class ListNoteFragment extends Fragment {
    ImageView ic_search,ic_setting;
    SearchView ed_search;
    RecyclerView recyclerViewList;
    TextView tv_title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewList = view.findViewById(R.id.list_note);
        ic_search = view.findViewById(R.id.ic_search);
        ed_search = view.findViewById(R.id.ed_search);
        ed_search.setVisibility(View.GONE);
        ic_setting = view.findViewById(R.id.img_setting);
        tv_title = view.findViewById(R.id.toolbar_title);
        ic_setting.setVisibility(View.VISIBLE);
        tv_title.setVisibility(View.VISIBLE);

        NoteObjDAO noteObjDAO = new NoteObjDAO(getContext());
        ArrayList<NoteSubject> listNote = noteObjDAO.selectAll();
        NoteAdapter adapter = new NoteAdapter(noteObjDAO.selectAll(), noteObjDAO);
        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationHide2(tv_title,ic_setting);
                AnimationShow(ed_search);

                ed_search.clearFocus();
                ed_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        ArrayList<NoteSubject> list = new ArrayList<>();

                        for(NoteSubject noteSubject : listNote){
                            if(noteSubject.getTitle().toLowerCase().contains(newText.toLowerCase())){
                                list.add(noteSubject);
                            }
                        }

                        if(list.isEmpty()){
                            Toast.makeText(getContext(), "Not found!", Toast.LENGTH_SHORT).show();
                        }else {
                            adapter.setFilter(list);
                        }
                        return true;
                    }
                });

            }
        });
        ic_search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AnimationShow2(tv_title,ic_setting);
                AnimationHide(ed_search);


                return true;
            }
        });



        recyclerViewList.setAdapter(adapter);
    }

    void AnimationShow(SearchView editText) {
        editText.setVisibility(View.VISIBLE);
        editText.setAlpha(0f);
        editText.setTranslationX(100);
        editText.animate().alpha(1f).translationXBy(-100).setDuration(1500);
    }

    void AnimationShow2(TextView tv, ImageView img) {
        tv.setVisibility(View.VISIBLE);
        img.setVisibility(View.VISIBLE);
        img.setAlpha(0f);
        img.setTranslationX(-100);
        img.animate().alpha(1f).translationXBy(100).setDuration(1500);
        tv.setAlpha(0f);
        tv.setTranslationX(-100);
        tv.animate().alpha(1f).translationXBy(100).setDuration(1500);
    }


    void AnimationHide(SearchView editText) {
        editText.setAlpha(0f);
        editText.setTranslationZ(100);
        editText.animate().alpha(1f).translationZBy(-100).setDuration(1500);
        editText.setVisibility(View.GONE);
    }


    void AnimationHide2(TextView tv, ImageView img) {
        tv.setAlpha(0f);
        tv.setTranslationZ(100);
        tv.animate().alpha(1f).translationZBy(-100).setDuration(1500);
        img.setAlpha(0f);
        img.setTranslationZ(100);
        img.animate().alpha(1f).translationZBy(-100).setDuration(1500);
        tv.setVisibility(View.GONE);

        img.setVisibility(View.GONE);
    }

}













