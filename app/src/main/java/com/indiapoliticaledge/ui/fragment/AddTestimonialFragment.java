package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.indiapoliticaledge.databinding.AddTestimonialsLayoutBinding;
import com.indiapoliticaledge.model.Testimonial;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTestimonialFragment extends Fragment {
    AddTestimonialsLayoutBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddTestimonialsLayoutBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Testimonial testimonial = new Testimonial();
                testimonial.firstName = binding.firstNameEt.getText().toString();
                testimonial.lastName = binding.lastNameEt.getText().toString();
                if (!binding.testimonialDropDown.getText().toString().equals("Select Testimonial")) {
                    testimonial.rating = Integer.parseInt(binding.testimonialDropDown.getText().toString());
                }
                testimonial.country = binding.countryEdit.getText().toString();
                testimonial.state = binding.stateEdit.getText().toString();
                testimonial.city = binding.cityEdit.getText().toString();
                testimonial.testimonialsText = binding.testimonialEdit.getText().toString();
                Utils.showProgessBar(requireActivity());
                RetrofitClient.getInstance(requireActivity()).getRetrofitAPI().addTestimonial(testimonial).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Utils.hideProgessBar();
                        if (response.isSuccessful()) {
                            Toast.makeText(requireActivity(), "Testimonial Added successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Utils.hideProgessBar();
                    }
                });
            }
        });

    }
}
