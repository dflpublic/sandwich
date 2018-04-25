package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject        root          = new JSONObject(json);
            JSONObject        name          = root.getJSONObject("name");
            String            mainName      = name.getString("mainName");
            JSONArray         jaAlsoKnownAs = name.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnownAs   = new ArrayList<String>();

            for (int i = 0; i < jaAlsoKnownAs.length(); i++) {
                alsoKnownAs.add(jaAlsoKnownAs.getString(i));
            }

            String placeOfOrigin = root.getString("placeOfOrigin");
            String description   = root.getString("description");
            String image         = root.getString("image");

            JSONArray         jaIngredients = root.getJSONArray("ingredients");
            ArrayList<String> ingredients   = new ArrayList<String>();

            for (int i = 0; i < jaIngredients.length(); i++) {
                ingredients.add(jaIngredients.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image,
                    ingredients);
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
