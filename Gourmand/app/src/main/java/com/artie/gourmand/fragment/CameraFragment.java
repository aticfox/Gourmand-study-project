package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.CreatePostActivity;
import com.flurgle.camerakit.CameraView;

/**
 * Created by ANFIELD on 26/5/2560.
 */

public class CameraFragment extends Fragment {

    Button mButtonTakePhoto;
    CameraView mCameraView;

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

        mButtonTakePhoto = (Button) rootView.findViewById(R.id.button_take_photo);
        mButtonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (v.getId()) {
                    case R.id.button_take_photo :
                        intent = CreatePostActivity.getStartIntent(getContext());
                        break;
                    default:
                        return;
                }
                startActivity(intent);
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

}
