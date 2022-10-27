package vn.edu.poly.tailaph26495_appnote.FRAGMENT;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import vn.edu.poly.tailaph26495_appnote.ADAPTER.ViewPagerAdapter;
import vn.edu.poly.tailaph26495_appnote.R;

public class HomeFragment extends Fragment {
    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    TextView dots[];
    ViewPagerAdapter viewPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSlideViewPager = view.findViewById(R.id.slide_on_boarding);
        mDotLayout = view.findViewById(R.id.indicator_layout);


        viewPagerAdapter = new ViewPagerAdapter(getContext());
        mSlideViewPager.setAdapter(viewPagerAdapter);
        setUpIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListner);

    }


    public void setUpIndicator(int position){
        dots = new TextView[6];
        mDotLayout.removeAllViews();
        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dots[i].setTextColor(getResources().getColor(R.color.inactive,getContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }
         dots[position].setTextColor(getResources().getColor(R.color.active,getContext().getTheme()));

    }
    ViewPager.OnPageChangeListener  viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };





}
