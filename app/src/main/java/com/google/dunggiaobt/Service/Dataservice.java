package com.google.dunggiaobt.Service;

import com.google.dunggiaobt.Model.Album;
import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.Model.Category;
import com.google.dunggiaobt.Model.Playlist;
import com.google.dunggiaobt.Model.Quangcao;
import com.google.dunggiaobt.Model.Topic;
import com.google.dunggiaobt.Model.TopicAndCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();
    //truong
    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();
    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();
    //Vu
    @GET("playlistforcurrentday%20.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();
    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHatHot();
    @FormUrlEncoded
    @POST("danhsachcacbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);
    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateluotthich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);


    //Cong
    @GET("TopicAndCategory.php")
    Call<TopicAndCategory> GetTopicAndCategory();

    @FormUrlEncoded
    @POST("congtruong.php")
    Call<List<BaiHat>> GetListOfSongsByTheme(@Field("idCategory") String idCategory);

    @GET("AllTopics.php")
    Call<List<Topic>> GetAllTopics();

    @FormUrlEncoded
    @POST("CategoryForTopic.php")
    Call<List<Category>> GetCategoriesForTopic(@Field("idTopic") String idTopic);

    //Ha
    @FormUrlEncoded
    @POST("danhsachcacbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaitheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> GetDanhsachcacPlaylist();
    //begin truong
    @FormUrlEncoded
    @POST("congtruong.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoalbum(@Field("idalbum") String idalbum);
    //end truong

    //Ha
    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);

}
