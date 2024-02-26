package com.indiapoliticaledge.ui.fragment;

import static com.indiapoliticaledge.utils.Constants.LOGIN_RESPONSE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.CandidateProfileFragmentBinding;
import com.indiapoliticaledge.model.NoticeBoardResponse;
import com.indiapoliticaledge.model.NoticeMessagesList;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.ConstituencyAV;
import com.indiapoliticaledge.network.responsemodel.ConstituencyImage;
import com.indiapoliticaledge.network.responsemodel.MlaInfo;
import com.indiapoliticaledge.network.responsemodel.MyAV;
import com.indiapoliticaledge.network.responsemodel.MyImage;
import com.indiapoliticaledge.network.responsemodel.SignInResponseModel;
import com.indiapoliticaledge.ui.adapter.ConstituencyAVAdapter;
import com.indiapoliticaledge.ui.adapter.ConstituencyImagesAdapter;
import com.indiapoliticaledge.ui.adapter.HomeNoticeAdapter;
import com.indiapoliticaledge.ui.adapter.MyAVAdapter;
import com.indiapoliticaledge.ui.adapter.MyImagesAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.SharedPref;
import com.indiapoliticaledge.utils.Utils;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateProfileFragment extends Fragment {

    CandidateProfileFragmentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CandidateProfileFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View custom_title_view = view.findViewById(R.id.custom_title_view);
        TextView title_txt = custom_title_view.findViewById(R.id.title_txt);
        title_txt.setText(getString(R.string.candidate_profile));

        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        Log.d("TAG", "onViewCreated: " + jsonObjectUser);


        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        bindVoterInfo(userInfo);
        bindMLAInfo();

        String noticeMessages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.NOTICE_MESSAGES);
        Log.d("TAG", "onResponse noticeMessages: " + noticeMessages);
        if (noticeMessages != null && !noticeMessages.equals("")) {
            List<NoticeMessagesList> noticeMessagesLists = Arrays.asList(new Gson().fromJson(noticeMessages, NoticeMessagesList[].class));
            binding.noticeMessagesList.setAdapter(new HomeNoticeAdapter(requireActivity(), noticeMessagesLists));
        } else {
            getAllNoticeMessages(userInfo.getUserId());
        }

        String myImages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.MY_IMAGES);
        Log.d("TAG", "onResponse: " + myImages);
        if (myImages != null && !myImages.equals("")) {
            List<MyImage> myImageList = Arrays.asList(new Gson().fromJson(myImages, MyImage[].class));
            if (myImageList != null && myImageList.size() > 0) {
                binding.myImagesSectionCard.setVisibility(View.VISIBLE);
                binding.myImagesList.setAdapter(new MyImagesAdapter(requireActivity(), myImageList));
            } else {
                binding.myImagesSectionCard.setVisibility(View.GONE);
            }
        } else {
            binding.myImagesSectionCard.setVisibility(View.GONE);
        }

        String constituencyImages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.CONSTITUENCY_IMAGES);
        Log.d("TAG", "onResponse constituencyImages: " + constituencyImages);
        if (constituencyImages != null && !constituencyImages.equals("")) {
            List<ConstituencyImage> myImageList = Arrays.asList(new Gson().fromJson(constituencyImages, ConstituencyImage[].class));
            if (myImageList != null && myImageList.size() > 0) {
                binding.constituencyImagesSectionCard.setVisibility(View.VISIBLE);
                binding.constituencyImagesList.setAdapter(new ConstituencyImagesAdapter(requireActivity(), myImageList));
            } else {
                binding.constituencyImagesSectionCard.setVisibility(View.GONE);
            }
        } else {
            binding.constituencyImagesSectionCard.setVisibility(View.VISIBLE);
        }

        String myAVs = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.MY_VIDEOS);
        Log.d("TAG", "onResponse MyAVs: " + myAVs);
        if (myAVs != null && !myAVs.equals("")) {
            List<MyAV> myImageList = Arrays.asList(new Gson().fromJson(myAVs, MyAV[].class));
            if (myImageList != null && myImageList.size() > 0) {
                binding.myVideosSectionCard.setVisibility(View.VISIBLE);
                binding.myVideosList.setAdapter(new MyAVAdapter(requireActivity(), myImageList));
            } else {
                binding.myVideosSectionCard.setVisibility(View.GONE);
            }
        } else {
            binding.myVideosSectionCard.setVisibility(View.GONE);
        }

        String constituencyVideos = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.CONSTITUENCY_VIDEOS);
        Log.d("TAG", "onResponse MyAVs: " + constituencyVideos);
        if (constituencyVideos != null && !constituencyVideos.equals("")) {
            List<ConstituencyAV> constituencyAVS = Arrays.asList(new Gson().fromJson(constituencyVideos, ConstituencyAV[].class));
            if (constituencyAVS.size() > 0) {
                binding.constituencyVideosSectionCard.setVisibility(View.VISIBLE);
                binding.constituencyVideosList.setAdapter(new ConstituencyAVAdapter(requireActivity(), constituencyAVS));
            } else {
                binding.constituencyVideosSectionCard.setVisibility(View.GONE);
            }
        } else {
            binding.constituencyVideosSectionCard.setVisibility(View.GONE);
        }

        binding.candidateManifestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginResponse = SharedPref.getmSharedPrefInstance(requireActivity()).getString(LOGIN_RESPONSE);
                SignInResponseModel signInResponseModel = new Gson().fromJson(loginResponse, SignInResponseModel.class);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(signInResponseModel.manifestFilePath));
                startActivity(i);
            }
        });

    }

    private void bindVoterInfo(UserInfo userInfo) {


        binding.firstNameEt.setText(userInfo.getFirstName() + " " + userInfo.getLastName());
        binding.partyEdit.setText(userInfo.birthPlace);
        binding.voterIdNumber.setText(userInfo.voterIdNumber);
        binding.shortDescEdit.setText(userInfo.shortDescriptionAbout);

        String loginResponse = SharedPref.getmSharedPrefInstance(requireActivity()).getString(LOGIN_RESPONSE);
        if (!loginResponse.isEmpty()) {
            SignInResponseModel signInResponseModel = new Gson().fromJson(loginResponse, SignInResponseModel.class);
            binding.stateDropDown.setText(signInResponseModel.constituencyState);
            binding.districtDropDown.setText(signInResponseModel.constituencyDistrict);
            binding.constituencyDropDown.setText(signInResponseModel.constituencyName);
        }

    }

    private void bindMLAInfo() {
        String mlaInfo = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.MLA_INFO);
        if (mlaInfo != null && !mlaInfo.equals("")) {
            MlaInfo userInfo = new Gson().fromJson(mlaInfo, MlaInfo.class);
            binding.mlaName.setText(userInfo.firstName + " " + userInfo.lastName);
            binding.constituencyNameTxt.setText(userInfo.constituency.constituencyName);
            if (userInfo.constituency != null) {
                binding.startDateValueTxt.setText(userInfo.constituency.constituencyName);
                binding.endDateValueTxt.setText(userInfo.partyName);
            }
            Glide.with(this).load(userInfo.profilePhotoUrl).placeholder(R.drawable.ic_logo).into(binding.profileImage);
            Glide.with(this).load(userInfo.partyLogo).placeholder(R.drawable.ic_logo).into(binding.partyIcon);
        }


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


}
