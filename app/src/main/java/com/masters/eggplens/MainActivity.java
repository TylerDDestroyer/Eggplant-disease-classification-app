package com.masters.eggplens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import java.io.ByteArrayOutputStream;
import com.masters.eggplens.ml.Mnv2v4;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_IMAGE = 2;
    private ImageView imageView;

    private int defaultImageRes;
    int imageSize = 224;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int item_id = item.getItemId();

        if (item_id == R.id.menuAbout){
            Intent intent = new Intent(this, AboutPage.class);
            startActivity(intent);
            return true;
        }

        if (item_id == R.id.menuHow){
            Intent intent = new Intent(this, HowToUsePage.class);
            startActivity(intent);
            return true;
        }

        if (item_id == R.id.menuExit) {
            finish(); // Close the app
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            refreshActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Refresh activity
    private void refreshActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imageView = findViewById(R.id.imageView);
        Button btnTakePhoto = findViewById(R.id.btnTakePhoto);
        Button btnUploadFile = findViewById(R.id.btnUploadFile);


        btnTakePhoto.setOnClickListener(view -> openCamera());
        btnUploadFile.setOnClickListener(view -> openGallery());

        defaultImageRes = R.mipmap.eggplant_foreground;

    }
    private void resetImageView() {
        imageView.setImageResource(defaultImageRes);
    }
    private void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }else{
                Uri dat = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void classifyImage(Bitmap image) {
        try {
            // Check if image dimensions match the expected size
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            if (imageWidth != imageSize || imageHeight != imageSize) {
                Log.e("Debug", "Image dimensions do not match the expected size: " + imageSize);
                return;
            }
            Mnv2v4 model = Mnv2v4.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++]; // Get pixel

                    // Extract RGB values and normalize
                    byteBuffer.putFloat(((val >> 16) & 0xFF) / 255.0f); // Red
                    byteBuffer.putFloat(((val >> 8) & 0xFF) / 255.0f);  // Green
                    byteBuffer.putFloat((val & 0xFF) / 255.0f);         // Blue
                }
            }
            inputFeature0.loadBuffer(byteBuffer);

            Mnv2v4.Outputs outputs = model.process(inputFeature0);
            String resultText = getString(outputs);

            // Convert the image to a byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();


            // Pass the result and the image to ResultActivity
            Intent intent = new Intent(MainActivity.this, ResultActivityActivity.class);
            intent.putExtra("result", resultText);
            intent.putExtra("image", byteArray);
            startActivity(intent);

            resetImageView();

            model.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error processing image. Please try again.", Toast.LENGTH_SHORT).show();

        }
    }

    @NonNull
    private static String getString(Mnv2v4.Outputs outputs) {
        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

        float[] confidences = outputFeature0.getFloatArray();
        float threshold = 0.7f;
        // Find the index of the class with the biggest confidence.
        int maxPos = 0;
        float maxConfidence = 0;
        String[] classes = {"Aphids", "Cercospora Leaf Spot", "Defect Eggplant", "Flea Beetles", "Fresh Eggplant", "Fresh Eggplant Leaf", "Leaf Wilt", "Phytophthora Blight", "Powdery Mildew", "Tobacco Mosaic Virus"};
        for (int i = 0; i < confidences.length; i++) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i];
                maxPos = i;
            }
        }
        String resultText;
        if (maxConfidence >= threshold) {
            resultText = classes[maxPos];
        } else {
            resultText = "Not an eggplant or related disease image. Please take a clearer picture and try again.";
        }
        return resultText;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission is required to use camera", Toast.LENGTH_SHORT).show();

            }
        }
    }
}