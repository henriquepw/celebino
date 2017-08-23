package org.celebino.celebinoapp.conection;

import org.celebino.celebinoapp.entities.AirConditioning;
import org.celebino.celebinoapp.entities.Garden;
import org.celebino.celebinoapp.entities.GardenStatus;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Henrique Martins on 14/06/2017.
 */

public interface APIService {

    /********************
     * Air Conditioning *
     ********************/
    @GET("airconditioning/")
    Call<List<AirConditioning>> getAllAirConditioning();

    @POST("airconditioning/")
    Call<AirConditioning> insertAirConditioning(@Body AirConditioning airConditioning);

    @PUT("airconditioning/")
    Call<AirConditioning> updateAirConditioning(@Body AirConditioning airConditioning);

    /********************
     * Liters minutes *
     ********************/
    @GET("litersminute/")
    Call<List<LitersMinute>> getAllLitersMinute();

    @GET("litersminute/byair/{id}")
    Call<List<LitersMinute>> findByIdLitersMinute(@Path("id") Long id);

    @POST("litersminute/")
    Call<LitersMinute> insertLitersMinute(@Body LitersMinute litersMinute);

    @PUT("litersminute/")
    Call<LitersMinute> updateLitersMinute(@Body LitersMinute litersMinute);

    /**********
     * Garden *
     **********/
    @GET("garden/")
    Call<List<Garden>> getAllGarden();

    @GET("garden/{id}")
    Call<List<Garden>> findGardenById(@Path("id") Long id);

    @POST("garden/")
    Call<Garden> insertGarden(@Body Garden garden);

    @PUT("garden/")
    Call<Garden> updateGarden(@Body Garden garden);

    /****************
     * GardenStatus *
     ****************/
    @GET("gardenstatus/")
    Call<List<GardenStatus>> getAllGardenStatus();

    @GET("gardenstatus/garden/{id}")
    Call<List<GardenStatus>> findGardenStatusById(@Path("id") Long id);

    @POST("gardenstatus/")
    Call<GardenStatus> insertGardenStatus(@Body GardenStatus gardenStatus);

    @PUT("gardenstatus/")
    Call<GardenStatus> updateGardenStatus(@Body GardenStatus gardenStatus);


}
