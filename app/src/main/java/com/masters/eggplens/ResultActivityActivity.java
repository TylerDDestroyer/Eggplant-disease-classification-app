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

        solutions.put("Healthy Leaf", "No issues detected. Keep monitoring the plant regularly to ensure it remains healthy.");
        solutions.put("Insect Pest Disease", "Apply appropriate insecticides or natural pest repellents. Ensure proper irrigation and remove affected leaves.");
        solutions.put("Leaf Spot Disease", "Use fungicides to treat the spots. Remove and destroy infected leaves to prevent further spread.");
        solutions.put("Mosaic Virus Disease", "Remove and destroy infected plants to prevent spread. Use virus-free seeds and control aphid populations.");
        solutions.put("Small Leaf Disease", "Ensure proper soil fertility. Use fertilizers rich in essential nutrients like nitrogen and potassium.");
        solutions.put("White Mold Disease", "Improve air circulation by spacing plants appropriately. Apply fungicides if necessary.");
        solutions.put("Wilt Disease", "Check for root rot and ensure proper drainage. Remove severely affected plants to prevent spreading.");
        solutions.put("Not an eggplant or related disease image. Please try again.", "Ensure the image is of an eggplant leaf or disease. Retake or upload a clearer image.");

        return solutions.getOrDefault(result, "Solution not available. Please consult an agricultural expert.");
    }
}