package com.indiapoliticaledge.ui.fragment;

import static com.indiapoliticaledge.utils.Constants.LOGIN_RESPONSE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.indiapoliticaledge.databinding.VoterProfileFragmentBinding;
import com.indiapoliticaledge.network.responsemodel.CandidatesList;
import com.indiapoliticaledge.network.responsemodel.SignInResponseModel;
import com.indiapoliticaledge.utils.SharedPref;

public class VoterProfileFragment extends Fragment {

    VoterProfileFragmentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = VoterProfileFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        CandidatesList candidatesList = (CandidatesList) arguments.getSerializable("candidatesList");

        if (candidatesList != null) {
            bindVoterInfo(candidatesList);
        }
//        View custom_title_view = view.findViewById(R.id.custom_title_view);
//        TextView title_txt = custom_title_view.findViewById(R.id.title_txt);
//        title_txt.setText(getString(R.string.update_voter_info));

//        Bundle bundle = getArguments();
//        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
//        Log.d("TAG", "onViewCreated: " + jsonObjectUser);


//        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
//        bindVoterInfo(userInfo);

//        String noticeMessages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.NOTICE_MESSAGES);
//        Log.d("TAG", "onResponse noticeMessages: " + noticeMessages);
//        if (noticeMessages != null && !noticeMessages.equals("")) {
//            List<NoticeMessagesList> noticeMessagesLists = Arrays.asList(new Gson().fromJson(noticeMessages, NoticeMessagesList[].class));
//            binding.noticeMessagesList.setAdapter(new HomeNoticeAdapter(requireActivity(), noticeMessagesLists));
//        } else {
//            getAllNoticeMessages(userInfo.getUserId());
//        }
//
//        String myImages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.MY_IMAGES);
//        Log.d("TAG", "onResponse: " + myImages);
//        if (myImages != null && !myImages.equals("")) {
//            List<MyImage> myImageList = Arrays.asList(new Gson().fromJson(myImages, MyImage[].class));
//            if (myImageList != null && myImageList.size() > 0) {
//                binding.myImagesSectionCard.setVisibility(View.VISIBLE);
//                binding.myImagesList.setAdapter(new MyImagesAdapter(requireActivity(), myImageList));
//            } else {
//                binding.myImagesSectionCard.setVisibility(View.GONE);
//            }
//        } else {
//            binding.myImagesSectionCard.setVisibility(View.GONE);
//        }
//
//        String constituencyImages = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.CONSTITUENCY_IMAGES);
//        Log.d("TAG", "onResponse constituencyImages: " + constituencyImages);
//        if (constituencyImages != null && !constituencyImages.equals("")) {
//            List<ConstituencyImage> myImageList = Arrays.asList(new Gson().fromJson(constituencyImages, ConstituencyImage[].class));
//            if (myImageList != null && myImageList.size() > 0) {
//                binding.constituencyImagesSectionCard.setVisibility(View.VISIBLE);
//                binding.constituencyImagesList.setAdapter(new ConstituencyImagesAdapter(requireActivity(), myImageList));
//            } else {
//                binding.constituencyImagesSectionCard.setVisibility(View.GONE);
//            }
//        } else {
//            binding.constituencyImagesSectionCard.setVisibility(View.VISIBLE);
//        }
//
//        String myAVs = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.MY_VIDEOS);
//        Log.d("TAG", "onResponse MyAVs: " + myAVs);
//        if (myAVs != null && !myAVs.equals("")) {
//            List<MyAV> myImageList = Arrays.asList(new Gson().fromJson(myAVs, MyAV[].class));
//            if (myImageList != null && myImageList.size() > 0) {
//                binding.myVideosSectionCard.setVisibility(View.VISIBLE);
//                binding.myVideosList.setAdapter(new MyAVAdapter(requireActivity(), myImageList));
//            } else {
//                binding.myVideosSectionCard.setVisibility(View.GONE);
//            }
//        } else {
//            binding.myVideosSectionCard.setVisibility(View.GONE);
//        }
//
//        String constituencyVideos = SharedPref.getmSharedPrefInstance(requireActivity()).getString(Constants.CONSTITUENCY_VIDEOS);
//        Log.d("TAG", "onResponse MyAVs: " + constituencyVideos);
//        if (constituencyVideos != null && !constituencyVideos.equals("")) {
//            List<ConstituencyAV> constituencyAVS = Arrays.asList(new Gson().fromJson(constituencyVideos, ConstituencyAV[].class));
//            if (constituencyAVS.size() > 0) {
//                binding.constituencyVideosSectionCard.setVisibility(View.VISIBLE);
//                binding.constituencyVideosList.setAdapter(new ConstituencyAVAdapter(requireActivity(), constituencyAVS));
//            } else {
//                binding.constituencyVideosSectionCard.setVisibility(View.GONE);
//            }
//        } else {
//            binding.constituencyVideosSectionCard.setVisibility(View.GONE);
//        }
//
//        binding.candidateManifestBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String loginResponse = SharedPref.getmSharedPrefInstance(requireActivity()).getString(LOGIN_RESPONSE);
//                SignInResponseModel signInResponseModel = new Gson().fromJson(loginResponse, SignInResponseModel.class);
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(signInResponseModel.manifestFilePath));
//                startActivity(i);
//            }
//        });

    }

    private void bindVoterInfo(CandidatesList userInfo) {


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


//    private void getAllNoticeMessages(int userId) {
//        Utils.showProgessBar(requireActivity());
//        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(requireContext()).getRetrofitAPI();
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("userId", userId);
//        retrofitAPI.getAllNoticeBoardMessages(jsonObject).enqueue(new Callback<NoticeBoardResponse>() {
//            @Override
//            public void onResponse(Call<NoticeBoardResponse> call, Response<NoticeBoardResponse> response) {
//                Utils.hideProgessBar();
//                if (response.isSuccessful()) {
//                    binding.noticeMessagesList.setAdapter(new HomeNoticeAdapter(requireActivity(), response.body().noticeMessagesList));
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<NoticeBoardResponse> call, Throwable t) {
//                Utils.hideProgessBar();
//            }
//        });
//    }


}
