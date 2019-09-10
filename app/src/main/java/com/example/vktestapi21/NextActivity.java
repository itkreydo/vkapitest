package com.example.vktestapi21;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vktestapi21.Models.VKUser;
import com.example.vktestapi21.requests.VKUsersRequest;
import com.squareup.picasso.Picasso;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.exceptions.VKApiExecutionException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NextActivity extends Activity {
    @BindView(R.id.imageView)
    ImageView avatarIV;
    @BindView(R.id.textView)
    TextView name;
    @BindView(R.id.textView2)
    TextView surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);

        requestUsers2();
//        VK.logout();
    }

    private void requestUsers2(){
        VKApiCallback<List<VKUser>> callback = new VKApiCallback<List<VKUser>>(){

            @Override
            public void success(List<VKUser> vkUsers) {
                VKUser user = vkUsers.get(0);
                name.setText(user.getFirstName());
                surname.setText(user.getLastName());
                Picasso.get()
                        .load(user.getPhoto())
                        .error(R.drawable.ic_launcher_background)
                        .into(avatarIV);
            }

            @Override
            public void fail(@NotNull VKApiExecutionException e) {

            }
        };
        VK.execute(new VKUsersRequest(new int[]{1}), callback);
    }

}
