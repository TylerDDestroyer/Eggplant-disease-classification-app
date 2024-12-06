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
        solutions.put("Insect Pest Disease", "• Apply proper fertilization. \n \n • Avoid over-fertilizing with nitrogen to reduce attractiveness to aphids. \n \n •Manage weeds before they flower and produce seeds to prevent insect pests and overwintering sites. \n \n • Handpick larger, slow-moving insects (e.g., hornworms, Colorado potato beetles). \n \n • Remove insects from plants and either squish them or drop them into soapy water. \n \n • Create habitats for beneficial insects by planting small flowering plants (e.g., sweet alyssum,dill, cilantro, fennel, rosemary). \n \n • Encourage parasitic wasps and flies that prey on pests like aphids, caterpillars, stink bugs,and leaf-footed bugs. \n \n • Avoid broad-spectrum insecticides to protect beneficial insects unless absolutely necessary.");
        solutions.put("Leaf Spot Disease", "• Maintain proper field sanitation: Collect and bury infected crop residues; remove and destroy severely diseased leaves.\n \n• Maintain proper field sanitation: Collect and bury infected crop residues; remove and destroy severely diseased leaves.\n \n• Control weeds to reduce relative humidity in the eggplant canopy.\n \n• Irrigate in the morning to reduce humid and damp conditions overnight.\n \n• Avoid over-irrigation to reduce relative humidity.\n \n• Avoid overhead sprinkler irrigation in order to minimize leaf wetness and spread of the pathogen in splashing water droplets.\n \n• Do not work with wet plants or move through a field of wet plants, as such movement can disperse fungal spores among plants.\n \n• Increase the spacing between plants to improve aeration and drying of wet foliage.\n \n• Keep plants adequately fertilized.\n \n• Intercrop eggplant with other vegetables to interrupt pathogen transmission between plants.\n \n• Grow eggplant under cover (i.e., in greenhouses or under shadecloth) where possible to minimize leaf wetness.\n \n• A calendar-based spray program using a protectant fungicide, combined with cultural practices, can reduce losses from Cercospora leaf spot on eggplant.");
        solutions.put("Mosaic Virus Disease", "• Sanitize the remnants of the previous crop, especially those that are host to the virus such as nightshades, legumes etc. Avoid planting near the fields and gardens where the nightshade crops are in the harvesting stage. Clean up weeds and weeds around the border. \n \n • Should plant early and focus to avoid spreading diseases to each other. \n \n • Use varieties resistant to sucking pests, and resistant to viruses. \n \n • Keep the field moisture appropriate, do not let it dry out. \n \n • Uproot and destroy diseased plants at a young stage, do not throw diseased plants indiscriminately. \n \n • Avoid over-fertilizing nitrogen fertilizers. \n \n • Avoid over-fertilizing nitrogen fertilizers.");
        solutions.put("Small Leaf Disease", "• Apply a balanced fertilizer to ensure the plant receives essential nutrients like nitrogen, phosphorus, and potassium. Organic compost or specific fertilizers designed for eggplants can help. \n \n • Test the soil to identify any nutrient imbalances or deficiencies. Amend the soil as needed with the appropriate nutrients. \n \n •  Prune off and dispose of infected leaves to reduce the spread of the disease. \n \n •  Ensure proper spacing between plants and avoid overhead watering to reduce moisture, which can encourage fungal growth. \n \n • Ensure the plant receives consistent watering. Avoid overwatering or underwatering, as this can lead to stress. \n \n • If the plants are exposed to extreme heat, provide shade or grow them in areas with indirect sunlight. \n \n • Apply mulch around the base of the plant to retain moisture and regulate soil temperature.");
        solutions.put("White Mold Disease", "• Remove and destroy infected plant parts to reduce fungal spread. \n \n • Implement crop rotation and deep tillage to reduce the soil sclerotia. \n \n • Apply approved fungicides like boscalid or chlorothalonil at disease onset.");
        solutions.put("Wilt Disease", "• Proper watering - Regulating irrigation prevents water stress, avoiding over or under-watering. \n \n •  Effective drainage - Implementing a well-draining soil medium can mitigate the issues of waterlogging and root rot.•\n \n • Fungicide application - Broad-spectrum fungicides can control fungal pathogens. \n \n • Insecticides - Using targeted insecticides can reduce pest populations that spread bacterial wilt.");
        solutions.put("Not an eggplant or related disease image. Please try again.", "Ensure the image is of an eggplant leaf or disease. Retake or upload a clearer image.");

        return solutions.getOrDefault(result, "Solution not available. Please consult an agricultural expert.");
    }
}