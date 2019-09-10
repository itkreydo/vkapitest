package com.example.vktestapi21.requests;

import com.example.vktestapi21.Models.VKUser;
import com.vk.api.sdk.requests.VKRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VKUsersRequest extends VKRequest<List<VKUser>> {

    public VKUsersRequest(int[] uids) {
        super("users.get");
        addParam("user_ids", myJoin(uids, ","));
        addParam("fields", "photo_200");
    }

    public VKUsersRequest() {
        super("users.get");
        addParam("fields", "photo_200");
    }

    @Override
    public List<VKUser> parse(JSONObject r) throws JSONException {
        JSONArray users = r.getJSONArray("response");
        ArrayList<VKUser> result = new ArrayList<VKUser>();
        for (int i = 0; i < users.length(); i++) {
            result.add(VKUser.parse(users.getJSONObject(i)));
        }
        return result;
    }

    public static String myJoin(int[] arr, String separator) {
        if (null == arr || 0 == arr.length) return "";

        StringBuilder sb = new StringBuilder(256);
        sb.append(arr[0]);

        //if (arr.length == 1) return sb.toString();

        for (int i = 1; i < arr.length; i++) sb.append(separator).append(arr[i]);

        return sb.toString();
    }
}
