package vn.edu.poly.tailaph26495_appnote.ADAPTER;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import vn.edu.poly.tailaph26495_appnote.R;


public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int images[] = {
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6
    };

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

       View view = LayoutInflater.from(context).inflate(R.layout.slide_onboarding_content,container,false);
       ImageView imgTitle = view.findViewById(R.id.title_img_onboarding);
        imgTitle.setImageResource(images[position]);
        container.addView(view);
       return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout) object);
    }

}















