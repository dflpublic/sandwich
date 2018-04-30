package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final  String EXTRA_POSITION   = "extra_position";
    private static final int    DEFAULT_POSITION = -1;

    @BindView(R.id.also_known_tv)
    TextView tvAlsoKnownAs;
    @BindView(R.id.origin_tv)
    TextView tvOrigin;
    @BindView(R.id.description_tv)
    TextView tvDescription;
    @BindView(R.id.ingredients_tv)
    TextView tvIngredients;
    @BindView(R.id.image_iv)
    ImageView ingredientsIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

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
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_error)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
//        TextView tvAlsoKnownAs = findViewById(R.id.also_known_tv);
//        TextView tvOrigin      = findViewById(R.id.origin_tv);
//        TextView tvDescription = findViewById(R.id.description_tv);
//        TextView tvIngredients = findViewById(R.id.ingredients_tv);

//        tvAlsoKnownAs.setText(appendContent(sandwich.getAlsoKnownAs()));
        tvAlsoKnownAs.setText(TextUtils.join("\n", sandwich.getAlsoKnownAs()));

        tvOrigin.setText(sandwich.getPlaceOfOrigin());
        tvDescription.setText(sandwich.getDescription());

//        tvIngredients.setText(appendContent(sandwich.getIngredients()));
        tvIngredients.setText(TextUtils.join("\n", sandwich.getIngredients()));
    }

//now obsolete
//    private String appendContent(List<String> strings) {
//        /*
//        concatennate strings in a list together, separating them by a linefeed.
//        make sure the last linefeed is not included
//         */
//        StringBuffer sb = new StringBuffer();
//        for (String s : strings
//                ) {
//            sb.append(s + "\n");
//        }
//        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
//            return sb.substring(0, sb.length() - 1);
//        } else {
//            return "";
//        }
//    }
}
