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
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.TestimonialsList;
import com.indiapoliticaledge.ui.SuperAdminScreen;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateTestimonialFragment extends Fragment {
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
        if (requireActivity() instanceof SuperAdminScreen)
            ((SuperAdminScreen) requireActivity()).setTitleText("Update Testimonial");

        Bundle arguments = getArguments();
        assert arguments != null;
        TestimonialsList testimonialsList = (TestimonialsList) arguments.get(Constants.TESTIMONIAL);

        bindTestimonialData(testimonialsList);
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testimonialsList.firstName = binding.firstNameEt.getText().toString();
                testimonialsList.lastName = binding.lastNameEt.getText().toString();
                if (!binding.testimonialDropDown.getText().toString().equals("Select Testimonial") && !binding.testimonialDropDown.getText().toString().equals("")) {
                    testimonialsList.rating = Integer.parseInt(binding.testimonialDropDown.getText().toString());
                }
                testimonialsList.country = binding.countryEdit.getText().toString();
                testimonialsList.state = binding.stateEdit.getText().toString();
                testimonialsList.city = binding.cityEdit.getText().toString();
                testimonialsList.testimonialsText = binding.testimonialEdit.getText().toString();
                Utils.showProgessBar(requireActivity());
                RetrofitClient.getInstance(requireActivity()).getRetrofitAPI().updateTestimonial(testimonialsList).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Utils.hideProgessBar();
                        if (response.isSuccessful()) {
                            Toast.makeText(requireActivity(), "Testimonial Updated successfully", Toast.LENGTH_SHORT).show();
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

    private void bindTestimonialData(TestimonialsList testimonialsList) {
        binding.firstNameEt.setText(testimonialsList.firstName);
        binding.lastNameEt.setText(testimonialsList.lastName);
        binding.testimonialEdit.setText(testimonialsList.testimonialsText);
        binding.stateEdit.setText(testimonialsList.state);
        binding.cityEdit.setText(testimonialsList.city);

    }
}
