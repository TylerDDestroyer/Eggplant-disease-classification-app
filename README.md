# Eggplant Disease Detection App

## Overview
The **Eggplant Disease Detection App** is a mobile application designed to assist farmers and agricultural professionals in identifying diseases affecting eggplant crops. The app uses a TensorFlow-trained image classification model to detect seven specific eggplant leaf diseases from captured images. Built with Android Studio, the app is user-friendly and accessible on Android devices.

## Features
- **Real-time Disease Detection**: Upload or capture an image of an eggplant leaf, and the app will classify the disease.
- **Seven Disease Classifications**: Focused detection for seven common eggplant diseases.
- **Mobile Accessibility**: Runs on Android devices, making it portable and convenient for field use.
- **User-Friendly Interface**: Simple and intuitive design for non-technical users.

## Technology Stack
- **TensorFlow**: For training the image classification model.
- **Android Studio**: For developing the Android application.
- **Languages**:
  - Python: Used for data preprocessing and training the TensorFlow model.
  - Java/Kotlin: Used for the Android application.

## Installation
### Prerequisites
1. Android Studio installed on your computer.
2. Python installed (for working with the TensorFlow model).
3. A physical or virtual Android device for testing.

### Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/eggplant-disease-detection.git
   ```
2. **Model Training** (Optional):
   - Navigate to the `model` directory and run the training script to train the TensorFlow model.
   - Ensure the training dataset is properly set up in the specified directory.

3. **Set Up the Android App**:
   - Open the `android-app` folder in Android Studio.
   - Sync the project with Gradle files.
   - Replace the placeholder TensorFlow Lite model (`.tflite` file) with your trained model in the `assets` folder.

4. **Build and Run**:
   - Connect an Android device or use an emulator.
   - Build and deploy the app from Android Studio.

## Future Enhancements
- Expand the number of disease classifications.
- Optimize the app for offline usage.
- Add multilingual support.
- Incorporate treatment recommendations.

## Contributing
Contributions are welcome! Please fork the repository, create a new branch, and submit a pull request.

## Acknowledgements
- TensorFlow community for the resources and tutorials.
- Android developers for their comprehensive documentation.
- Agricultural experts for helping identify relevant diseases.
