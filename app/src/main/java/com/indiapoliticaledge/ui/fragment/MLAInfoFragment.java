package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.MlaInfoFragmentBinding;
import com.indiapoliticaledge.model.NoticeBoardResponse;
import com.indiapoliticaledge.model.NoticeMessagesList;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.ConstituencyImage;
import com.indiapoliticaledge.network.responsemodel.MyImage;
import com.indiapoliticaledge.network.responsemodel.ViewMemberResponse;
import com.indiapoliticaledge.ui.adapter.ConstituencyImagesAdapter;
import com.indiapoliticaledge.ui.adapter.HomeNoticeAdapter;
import com.indiapoliticaledge.ui.adapter.MyImagesAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.SharedPref;
import com.indiapoliticaledge.utils.Utils;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MLAInfoFragment extends Fragment {
    private TextView mla_name, constituency_txt, start_date_value_txt, end_date_value_txt;
    ImageView profile_image, party_icon;
    WebView map_section_map;

    MlaInfoFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MlaInfoFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFields(view);
        Utils.showProgessBar(requireActivity());
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(requireContext()).getRetrofitAPI();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.getUserId());
        retrofitAPI.getMember(jsonObject).enqueue(new Callback<ViewMemberResponse>() {
            @Override
            public void onResponse(Call<ViewMemberResponse> call, Response<ViewMemberResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    ViewMemberResponse viewMemberResponse = response.body();
                    if (viewMemberResponse != null && viewMemberResponse.successCode.equals("200")) {
                        UserInfo userInfo = viewMemberResponse.getUserInfo();
                        if (userInfo != null) {
                            bindUserInfo(viewMemberResponse);

                            String noticeMessages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.NOTICE_MESSAGES);
                            if (noticeMessages != null && !noticeMessages.equals("")) {
                                List<NoticeMessagesList> noticeMessagesLists = Arrays.asList(new Gson().fromJson(noticeMessages, NoticeMessagesList[].class));
                                binding.noticeMessagesList.setAdapter(new HomeNoticeAdapter(requireActivity(), noticeMessagesLists));
                            } else {
                                getAllNoticeMessages(userInfo.getUserId());
                            }

                            String myImages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.MY_IMAGES);
                            Log.d("TAG", "onResponse: "+myImages);
                            if (myImages != null && !myImages.equals("")) {
                                List<MyImage> myImageList = Arrays.asList(new Gson().fromJson(myImages, MyImage[].class));
                                binding.myImagesList.setAdapter(new MyImagesAdapter(requireActivity(), myImageList));
                            }

                            String constituencyImages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.CONSTITUENCY_IMAGES);
                            Log.d("TAG", "onResponse constituencyImages: "+constituencyImages);
                            if (constituencyImages != null && !constituencyImages.equals("")) {
                                List<ConstituencyImage> myImageList = Arrays.asList(new Gson().fromJson(constituencyImages, ConstituencyImage[].class));
                                binding.constituencyImagesList.setAdapter(new ConstituencyImagesAdapter(requireActivity(), myImageList));
                            }

                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ViewMemberResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });
    }

    private void getAllNoticeMessages(int userId) {
        Utils.showProgessBar(requireActivity());
        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(requireContext()).getRetrofitAPI();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        retrofitAPI.getAllNoticeBoardMessages(jsonObject).enqueue(new Callback<NoticeBoardResponse>() {
            @Override
            public void onResponse(Call<NoticeBoardResponse> call, Response<NoticeBoardResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    binding.noticeMessagesList.setAdapter(new HomeNoticeAdapter(requireActivity(), response.body().noticeMessagesList));
                }

            }

            @Override
            public void onFailure(Call<NoticeBoardResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });
    }

    private void initFields(View view) {
        mla_name = view.findViewById(R.id.mla_name);
        constituency_txt = view.findViewById(R.id.constituency_name_txt);
        start_date_value_txt = view.findViewById(R.id.start_date_value_txt);
        end_date_value_txt = view.findViewById(R.id.end_date_value_txt);
        profile_image = view.findViewById(R.id.profile_image);
        party_icon = view.findViewById(R.id.party_icon);
        map_section_map = view.findViewById(R.id.map_section_map);
    }

    private void bindUserInfo(ViewMemberResponse viewMemberResponse) {
        UserInfo userInfo = viewMemberResponse.userInfo;
        mla_name.setText(userInfo.firstName + " " + userInfo.lastName);
        constituency_txt.setText(viewMemberResponse.constituencyMap);
        if (userInfo.userConstituencies != null) {
            if (userInfo.userConstituencies.startDate != null) {
                start_date_value_txt.setText(userInfo.userConstituencies.startDate.split("\\s")[0]);
            }
            if (userInfo.userConstituencies.endDate != null) {
                end_date_value_txt.setText(userInfo.userConstituencies.endDate.split("\\s")[0]);
            }
        } else {
            if (userInfo.startDate != null) {
                start_date_value_txt.setText(userInfo.startDate.split("\\s")[0]);
            }
            if (userInfo.endDate != null) {
                end_date_value_txt.setText(userInfo.endDate.split("\\s")[0]);
            }
        }
        Glide.with(this).load(viewMemberResponse.userInfo.getProfilePhotoUrl()).placeholder(R.drawable.ic_logo).into(profile_image);
        Glide.with(this).load(viewMemberResponse.userInfo.getPartyLogo()).placeholder(R.drawable.ic_logo).into(party_icon);
//        start_date_value_txt.setText(viewMemberResponse.userConstituencies.startDate.split("\\s")[0]);
//        end_date_value_txt.setText(viewMemberResponse.userInfo.userConstituencies.endDate.split("\\s")[0]);

        if (viewMemberResponse != null) {
            String html = "<iframe src=\"https://maps.google.com/maps?width=100%&amp;height=100%&amp;hl=en&amp;q=" + viewMemberResponse.constituencyMap + ","
                    + viewMemberResponse.constituencyDistrict + "," + viewMemberResponse.constituencyState
                    + "&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed\" width=\"100%\" height=\"100%\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>";
            map_section_map.setInitialScale(1);
            map_section_map.setWebChromeClient(new WebChromeClient());
            map_section_map.getSettings().setAllowFileAccess(true);
            map_section_map.getSettings().setPluginState(WebSettings.PluginState.ON);
            map_section_map.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
            map_section_map.setWebViewClient(new WebViewClient());
            map_section_map.getSettings().setJavaScriptEnabled(true);
            map_section_map.getSettings().setLoadWithOverviewMode(true);
            map_section_map.getSettings().setUseWideViewPort(true);
            map_section_map.loadData(html, "text/html", null);
        }
    }
}
