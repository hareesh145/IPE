package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.indiapoliticaledge.databinding.TestimonialsLayoutBinding;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.TestimonialResponseModel;
import com.indiapoliticaledge.ui.adapter.TestimonialsAdapter;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestimonialsFragment extends Fragment {
    TestimonialsLayoutBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TestimonialsLayoutBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleteFlag", "N");
        RetrofitClient.getInstance(requireActivity()).getRetrofitAPI().getAllTestimonials(jsonObject).enqueue(new Callback<TestimonialResponseModel>() {
            @Override
            public void onResponse(Call<TestimonialResponseModel> call, Response<TestimonialResponseModel> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body().testimonialsList != null && response.body().testimonialsList.size() > 0) {
                        binding.noDataFoundTxt.setVisibility(View.GONE);
                        binding.testimonialList.setAdapter(new TestimonialsAdapter(requireActivity(), response.body().testimonialsList));
                    } else {
                        binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TestimonialResponseModel> call, Throwable t) {
                Utils.hideProgessBar();
                t.printStackTrace();
                binding.noDataFoundTxt.setVisibility(View.VISIBLE);
            }
        });

    }
}
