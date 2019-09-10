package com.example.vktestapi21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;
import com.vk.api.sdk.auth.VKScope;
import com.vk.api.sdk.utils.VKUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    ArrayList rights;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (VK.isLoggedIn()){
            Toast.makeText(this, "Already logged in", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, NextActivity.class);
            startActivity(i);
            finish();
        }

        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button_vk);
        rights = new ArrayList();
        rights.add(VKScope.WALL);
        rights.add(VKScope.PHOTOS);
        ButterKnife.bind(this);
//        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
//
//        Log.d("finger", Arrays.toString(fingerprints));

    }

    @OnClick(R.id.button_vk)
    public void authVk(){
        VK.login(this, rights);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKAuthCallback callback = new VKAuthCallback(){
            @Override
            public void onLogin(VKAccessToken token) {
                // Пользователь успешно авторизовался
                Toast.makeText(MainActivity.this, "Success auth", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), NextActivity.class);
                startActivity(i);
            }
            @Override
            public void onLoginFailed(int errorCode) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        };

        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
