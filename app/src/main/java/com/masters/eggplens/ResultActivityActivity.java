package com.masters.eggplens;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;

public class ResultActivityActivity extends AppCompatActivity {

    TextView resultTextView;
    TextView solutionTextView;
    ImageView resultImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        resultTextView = findViewById(R.id.resultTextView);
        solutionTextView = findViewById(R.id.solutionTextView);
        resultImageView = findViewById(R.id.resultImageView);

        // Get the result from the Intent
        String result = getIntent().getStringExtra("result");
        resultTextView.setText(result);

        // Get the image from the Intent
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        resultImageView.setImageBitmap(image);

        String solution = getSolution(result);
        solutionTextView.setText(solution);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private String getSolution(String result) {
        Map<String, String> solutions = new HashMap<>();

        solutions.put("Aphids", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Cercospora Leaf Spot", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Defect Eggplant", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Flea Beetles", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Fresh Eggplant", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Fresh Eggplant Leaf", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Leaf Wilt", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Pytophthora Blight", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Powdery Mildew", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        solutions.put("Tobacco Mosaic Virus", "• Apply proper fertilization.\n"
                + "• Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids.\n"
                + "• Manage weeds before they flower to prevent insect breeding.\n"
                + "• Handpick larger insects like hornworms and potato beetles.\n"
                + "• Encourage beneficial insects like parasitic wasps and ladybugs.\n"
                + "• Avoid broad-spectrum insecticides unless necessary.");

        return solutions.getOrDefault(result, "Solution not available.");
    }
}