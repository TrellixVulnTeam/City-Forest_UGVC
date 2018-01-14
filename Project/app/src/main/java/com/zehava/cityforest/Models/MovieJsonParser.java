package com.zehava.cityforest.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mapbox.services.commons.models.Position;

import org.json.JSONObject;

/**
 * Created by avigail on 10/01/18.
 */

public class MovieJsonParser
{
    public PointOfInterest parse(JSONObject jsonObject)
    {
        if (jsonObject == null)
            return null;
        String title = jsonObject.optString("title");
        String image = jsonObject.optString("snippet");
        String type = jsonObject.optString("type");
        String position = jsonObject.optString("position");
        Position pos = retrievePositionFromJson(position);
        double rating = pos.getLatitude();
        double year = pos.getLongitude();
        if (title != null && image != null && rating >= 0 && year != 0)
            return new PointOfInterest(rating, year,title, image,type);
        return null;
    }

    public Position retrievePositionFromJson(String posJs) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();

        Gson gson = gsonBuilder.create();
        Position obj = gson.fromJson(posJs, Position.class);
        return obj;
    }
}