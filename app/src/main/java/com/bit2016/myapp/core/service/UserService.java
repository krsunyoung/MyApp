package com.bit2016.myapp.core.service;

import com.bit2016.android.network.JSONResult;
import com.bit2016.myapp.core.domain.User;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bit-user1 on 2016-12-02.
 */

public class UserService {
    public List<User> fetchUserList(){
        String url = "http://192.168.1.21:8088/myapp-api/api/user/list"; //android 연결이므로 localhost는 안되
        HttpRequest httpRequest = HttpRequest.get(url);

        httpRequest.contentType(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.accept(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        //실제통신
        int responseCode = httpRequest.code();
        if(responseCode != HttpURLConnection.HTTP_OK){ //결과에 대한 정의가 있어서 HttpURLConnection.HTTP_OK를 잠시 써줌
            throw new RuntimeException("HTTP Response :" + responseCode);
        }

        JSONResultUserList jsonResult = fromJSON(httpRequest, JSONResultUserList.class);
        return  jsonResult.getData();
    }
    public List<User> fetchUserMockList(){

        //Mock Data
        List<User> list= new ArrayList<User>();

        User user = new User();
        user.setId( 1L );
        user.setName( "조인성" );
        user.setPhone( "010-1234-5678" );
        user.setEmail( "kick@korea.com" );
        user.setProfilePic("https://tv.pstatic.net/thm?size=120x150&quality=9&q=http://sstatic.naver.net/people/portrait/201604/20160425155600816.jpg");
        user.setStatus( 1 );
        list.add( user );

        return list;
    }

    private class JSONResultUserList extends JSONResult<List<User>>{

    }

    //JSON 문자열을 자바 객체로 변환
    protected <V> V fromJSON( HttpRequest request, Class<V> target )  {
        V v = null;
        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = request.bufferedReader();
            v = gson.fromJson(reader, target);

            reader.close();
        }catch ( Exception ex){
            throw new RuntimeException( ex );
        }
        return v;
    }

}
