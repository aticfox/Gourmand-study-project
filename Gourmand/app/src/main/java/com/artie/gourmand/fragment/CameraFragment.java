package com.artie.gourmand.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.CreatePostActivity;
import com.flurgle.camerakit.CameraListener;
import com.flurgle.camerakit.CameraView;

import java.io.ByteArrayOutputStream;

/**
 * Created by ANFIELD on 26/5/2560.
 */

public class CameraFragment extends Fragment {

    Button mButtonTakePhoto;
    CameraView mCameraView;

    private Uri mImageURI;

    public static CameraFragment newInstance() {
        Bundle args = new Bundle();
        CameraFragment fragment = new CameraFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(savedInstanceState);

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void init(Bundle savedInstanceState) {

    }

    private void initInstances(View rootView) {
        mCameraView = (CameraView) rootView.findViewById(R.id.camera);
        mCameraView.setCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);

                Bitmap result = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
                mImageURI = getImageUri(getContext(), result);

                Intent intent = CreatePostActivity.getStartIntent(getContext(), mImageURI);
                startActivity(intent);
            }
        });

        mButtonTakePhoto = (Button) rootView.findViewById(R.id.button_take_photo);
        mButtonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_take_photo :
                        mCameraView.captureImage();
                        break;
                    default:
                        return;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCameraView.stop();
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
