package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final  String EXTRA_POSITION   = "extra_position";
    private static final int    DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String   json       = sandwiches[position];
        Sandwich sandwich   = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView tvAlsoKnownAs = findViewById(R.id.also_known_tv);
        TextView tvOrigin      = findViewById(R.id.origin_tv);
        TextView tvDescription = findViewById(R.id.description_tv);
        TextView tvIngredients = findViewById(R.id.ingredients_tv);

        tvAlsoKnownAs.setText(appendContent(sandwich.getAlsoKnownAs()));

        tvOrigin.setText(sandwich.getPlaceOfOrigin());
        tvDescription.setText(sandwich.getDescription());

        tvIngredients.setText(appendContent(sandwich.getIngredients()));
    }

    private String appendContent(List<String> strings) {
        /*
        concatennate strings in a list together, separating them by a linefeed.
        make sure the last linefeed is not included
         */
        StringBuffer sb = new StringBuffer();
        for (String s : strings
                ) {
            sb.append(s + "\n");
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            return sb.substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }
}
