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
            JSONObject        name          = root.getJSONObject(NAME);
            String            mainName      = name.getString(MAIN_NAME);
            JSONArray         jaAlsoKnownAs = name.getJSONArray(ALSOKNWONAS);
            ArrayList<String> alsoKnownAs   = new ArrayList<String>();

            for (int i = 0; i < jaAlsoKnownAs.length(); i++) {
                alsoKnownAs.add(jaAlsoKnownAs.getString(i));
            }

            String placeOfOrigin = root.getString(ORIGIN);
            String description   = root.getString(DESCRIPTION);
            String image         = root.getString(IMAGE);

            JSONArray         jaIngredients = root.getJSONArray(INGREDIENTS);
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
