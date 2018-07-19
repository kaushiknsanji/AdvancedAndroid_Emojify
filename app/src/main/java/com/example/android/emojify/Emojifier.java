package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * Class to detect faces and overlay an appropriate
 * Emoji on each face based on the classification results.
 *
 * @author Kaushik N Sanji
 */
class Emojifier {
    // COMPLETED (1): Create a Java class called Emojifier
    // COMPLETED (2): Create a static method in the Emojifier class called detectFaces() which detects and logs the number of faces in a given bitmap.

    private static final String LOG_TAG = Emojifier.class.getSimpleName();

    /**
     * Method that detects the number of faces in the Bitmap #picture passed
     * and reports the same.
     *
     * @param context is the Context of the Application
     * @param picture is the Picture to be scanned for faces
     */
    static void detectFaces(Context context, Bitmap picture){

        //Creating a FaceDetector to detect the faces
        FaceDetector faceDetector = new FaceDetector.Builder(context)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)  //Do All Classifications
                .setTrackingEnabled(false) //No Face Tracking required
                .build();

        //Constructing the image metadata using Frame
        Frame frame = new Frame.Builder()
                .setBitmap(picture)
                .build();

        //Detecting the faces in the Frame using the Detector
        SparseArray<Face> faceSparseArray = faceDetector.detect(frame);

        //Number of Faces detected in the picture passed
        int noOfFacesDetected = faceSparseArray.size();

        Log.i(LOG_TAG, "detectFaces: Number of faces detected " + noOfFacesDetected);

        if(noOfFacesDetected == 0){
            Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
        }

        //Release the detector
        faceDetector.release();
    }
}
