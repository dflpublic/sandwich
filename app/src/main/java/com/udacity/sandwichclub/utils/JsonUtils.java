package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    public static String NAME        = "name";
    public static String MAIN_NAME   = "mainName";
    public static String ALSOKNWONAS = "alsoKnownAs";
    public static String ORIGIN      = "placeOfOrigin";
    public static String IMAGE       = "image";
    public static String DESCRIPTION = "description";
    public static String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject        root          = new JSONObject(json);
            JSONObject        name          = root.optJSONObject(NAME);
            String            mainName      = name.optString(MAIN_NAME);
            JSONArray         jaAlsoKnownAs = name.optJSONArray(ALSOKNWONAS);
            ArrayList<String> alsoKnownAs   = new ArrayList<String>();

            for (int i = 0; i < jaAlsoKnownAs.length(); i++) {
                alsoKnownAs.add(jaAlsoKnownAs.getString(i));
            }

            String placeOfOrigin = root.optString(ORIGIN);
            String description   = root.optString(DESCRIPTION);
            String image         = root.optString(IMAGE);

            JSONArray         jaIngredients = root.optJSONArray(INGREDIENTS);
            ArrayList<String> ingredients   = new ArrayList<String>();

            for (int i = 0; i < jaIngredients.length(); i++) {
                ingredients.add(jaIngredients.optString(i));
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
