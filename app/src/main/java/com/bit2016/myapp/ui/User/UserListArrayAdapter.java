package com.bit2016.myapp.ui.User;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bit2016.myapp.R;
import com.bit2016.myapp.core.domain.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by bit-user1 on 2016-12-02.
 */

public class UserListArrayAdapter extends ArrayAdapter<User> {
    private LayoutInflater layoutInflater;


    //통신 중 오류가 나면 이 이미지를 보여줘라라
    DisplayImageOptions displayImageOptions =  new DisplayImageOptions.Builder()
            // .showImageOnLoading( R.drawable.ic_default_profile )// resource or drawable
            .showImageForEmptyUri( R.drawable.ic_default_profile )// resource or drawable
            .showImageOnFail( R.drawable.ic_default_profile )// resource or drawable
            //.resetViewBeforeLoading( false )// default
            .delayBeforeLoading( 0 )
            //.cacheInMemory( false )// default
            .cacheOnDisc( true )// false is default
            //.preProcessor(...)
            //.postProcessor(...)
            //.extraForDownloader(...)
            //.considerExifParams( false )// default
            //.imageScaleType( ImageScaleType.IN_SAMPLE_POWER_OF_2 )// default
            //.bitmapConfig( Bitmap.Config.ARGB_8888 )// default
            //.decodingOptions(...)
            //.displayer( new SimpleBitmapDisplayer() )// default
            //.handler( new Handler() )// default
            .build();

    public UserListArrayAdapter(Context context){
        super( context, R.layout.fragment_user_list);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null ){
            view = layoutInflater.inflate(R.layout.row_user_list, parent, false);
        }
        //내부 ArrayList에서 해당 포지션의 User 객체를 받아옴
        User user = getItem(position);

        //프로필 세팅
        ImageLoader.getInstance().displayImage(user.getProfilePic(), (ImageView)view.findViewById(R.id.profile), displayImageOptions );

        //이름 세팅
        TextView textview =(TextView)view.findViewById(R.id.name);
        textview.setText(user.getName());

        return view;

    }

    public void add(List<User> users){
        if(users == null || users.size() ==0){
            return;
        }
        for(User user : users){
            add(user);
        }
//         notifyDataSetChanged();  add에 있기 때문에 생략이 가능하다.
    }
}
