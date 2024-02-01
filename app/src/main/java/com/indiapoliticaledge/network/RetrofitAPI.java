package com.indiapoliticaledge.network;

import com.google.gson.JsonObject;
import com.indiapoliticaledge.model.BoothLevelManagmentResponse;
import com.indiapoliticaledge.model.BoothLevelResponse;
import com.indiapoliticaledge.model.NoticeBoardResponse;
import com.indiapoliticaledge.model.Testimonial;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.requestmodel.AddOpinionRequest;
import com.indiapoliticaledge.network.requestmodel.CDevelopmentRequest;
import com.indiapoliticaledge.network.requestmodel.Candidate;
import com.indiapoliticaledge.network.requestmodel.ChangePassword;
import com.indiapoliticaledge.network.requestmodel.Donate;
import com.indiapoliticaledge.network.requestmodel.Member;
import com.indiapoliticaledge.network.requestmodel.SignInModel;
import com.indiapoliticaledge.network.requestmodel.UDevelopmentRequest;
import com.indiapoliticaledge.network.requestmodel.UpdateCandidate;
import com.indiapoliticaledge.network.requestmodel.UpdateOpinionPolling;
import com.indiapoliticaledge.network.responsemodel.AddMemberResponse;
import com.indiapoliticaledge.network.responsemodel.CandidateBiographyResponse;
import com.indiapoliticaledge.network.responsemodel.CandidateDonateResponse;
import com.indiapoliticaledge.network.responsemodel.CandidatesResponse;
import com.indiapoliticaledge.network.responsemodel.ConstituencyMapResponse;
import com.indiapoliticaledge.network.responsemodel.ConstituencyResponse;
import com.indiapoliticaledge.network.responsemodel.DepartmentResponse;
import com.indiapoliticaledge.network.responsemodel.DistrictResponse;
import com.indiapoliticaledge.network.responsemodel.FilteredVCDByYearsResponse;
import com.indiapoliticaledge.network.responsemodel.LatestNewsResponse;
import com.indiapoliticaledge.network.responsemodel.LeaderRatingResponse;
import com.indiapoliticaledge.network.responsemodel.MembersResponse;
import com.indiapoliticaledge.network.responsemodel.OpinionPollingResponse;
import com.indiapoliticaledge.network.responsemodel.OpinionResponse;
import com.indiapoliticaledge.network.responsemodel.Response;
import com.indiapoliticaledge.network.responsemodel.SignInResponseModel;
import com.indiapoliticaledge.network.responsemodel.StatesResponse;
import com.indiapoliticaledge.network.responsemodel.TestimonialResponseModel;
import com.indiapoliticaledge.network.responsemodel.TestimonialsList;
import com.indiapoliticaledge.network.responsemodel.VDevelopmentResponse;
import com.indiapoliticaledge.network.responsemodel.ViewConstituencyIssuesResponse;
import com.indiapoliticaledge.network.responsemodel.ViewMemberResponse;
import com.indiapoliticaledge.network.responsemodel.ViewPhotosResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("signin")
    Call<SignInResponseModel> signIn(@Body SignInModel signInModel);

    @POST("add-member")
    Call<AddMemberResponse> addMember(@Body Member member);

    //deleteFlag - Y to delete N to update
    @POST("update-member")
    Call<AddMemberResponse> updateMember(@Body UserInfo userInfo);

    @POST("add-candidate-profile")
    Call<AddMemberResponse> addCandidate(@Body Candidate candidate);

    @POST("update-candidate-profile")
    Call<AddMemberResponse> updateCandidateProfile(@Body UpdateCandidate updateCandidate);

    @POST("delete-candidate-profile")
    Call<AddMemberResponse> deleteCandidateProfile(@Body JsonObject jsonObject);

    @POST("change-password")
    Call<AddMemberResponse> changePassword(@Body ChangePassword changePassword);

    @POST("candidate-donate")
    Call<AddMemberResponse> candidateDonate(@Body Donate donate);

    @POST("view-candidates-donates")
    Call<CandidateDonateResponse> viewCandidateDonate(@Body JsonObject jsonObject);

    @POST("candidate-opinion-polling")
    Call<JsonObject> candidateOpinionPolling(@Body JsonObject jsonObject);

    @POST("view-candidate-opinion-polling")
    Call<OpinionPollingResponse> viewCandidateOpinionPolling(@Body JsonObject jsonObject);

    @POST("candidate-opinion-polling-update")
    Call<JsonObject> updateOpinionPolling(@Body UpdateOpinionPolling updateOpinionPolling);

    @POST("manage-members")
    Call<MembersResponse> manageMembers(@Body JsonObject jsonObject);


    @POST("view-member")
    Call<ViewMemberResponse> getMember(@Body JsonObject jsonObject);

    @POST("view-members-on-date")
    Call<MembersResponse> viewMembersOnDate(@Body JsonObject jsonObject);

    @POST("add-opinion")
    Call<Response> addOptions(@Body AddOpinionRequest addOpinionRequest);

    @POST("states")
    Call<StatesResponse> getStates(@Body JsonObject jsonObject);

    @POST("districts")
    Call<DistrictResponse> getDistricts(@Body JsonObject jsonObject);


    @POST("constituencies")
    Call<ConstituencyResponse> getConstituencies(@Body JsonObject jsonObject);

    @POST("all-candidates-list")
    Call<CandidatesResponse> getAllCandidatesList(@Body JsonObject jsonObject);

    @POST("all-departments")
    Call<DepartmentResponse> getAllDepartments(@Body JsonObject jsonObject);

    @POST("logout")
    Call<JsonObject> logout(@Body JsonObject jsonObject);

    @POST("manage-subscription")
    Call<JsonObject> manageSubscription(@Body JsonObject jsonObject);

    @POST("view-candidate-biography")
    Call<CandidateBiographyResponse> viewCandidateBiography(@Body JsonObject jsonObject);

    @POST("constituency-map")
    Call<ConstituencyMapResponse> constituencyMap(@Body JsonObject jsonObject);

    @POST("add-constituency-development")
    Call<AddMemberResponse> addConstituencyDevelopment(@Body CDevelopmentRequest cDevelopmentRequest);

    @POST("update-constituency-development")
    Call<AddMemberResponse> updateConstituencyDevelopment(@Body UDevelopmentRequest uDevelopmentRequest);

    @POST("delete-constituency-development")
    Call<JsonObject> deleteConstituencyDevelopment(@Body JsonObject jsonObject);

    @POST("view-constituency-development")
    Call<VDevelopmentResponse> viewConstituencyDevelopment(@Body JsonObject jsonObject);

    @POST("view-constituency-dev-by-years")
    Call<FilteredVCDByYearsResponse> viewConstituencyDevByYears(@Body JsonObject jsonObject);

    @POST("candidate-upload-photos")
    Call<JsonObject> uploadPhotos(@Body JsonObject jsonObject);

    @POST("delete-candidate-photos")
    Call<JsonObject> deletePhotos(@Body JsonObject jsonObject);

    @POST("view-candidate-uploaded-photos")
    Call<ViewPhotosResponse> viewPhotos(@Body JsonObject jsonObject);

    @POST("add-constituency-issues")
    Call<JsonObject> addConstituencyIssues(@Body JsonObject jsonObject);

    @POST("add-testimonials")
    Call<JsonObject> addTestimonial(@Body Testimonial testimonial);

    @POST("update-testimonials")
    Call<JsonObject> updateTestimonial(@Body TestimonialsList testimonialsList);

    @POST("all-testimonials")
    Call<TestimonialResponseModel> getAllTestimonials(@Body JsonObject jsonObject);


    @POST("all-noticeBoard-messages")
    Call<NoticeBoardResponse> getAllNoticeBoardMessages(@Body JsonObject jsonObject);

    @POST("all-constituency-issues")
    Call<ViewConstituencyIssuesResponse> getAllConstituencyIssues(@Body JsonObject jsonObject);

    @POST("leader-rating")
    Call<LeaderRatingResponse> getLeaderRating(@Body JsonObject jsonObject);

    @POST("all-latest-news")
    Call<LatestNewsResponse> getAllLatestNews(@Body JsonObject jsonObject);

    @POST("view-opinions")
    Call<OpinionResponse> manageOpinions(@Body JsonObject jsonObject);

    @POST("delete-testimonials")
    Call<JsonObject> deleteTestimonial(@Body JsonObject jsonObject);


    @POST("boothlevel-management")
    Call<BoothLevelResponse> getBoothLevelManagement(@Body JsonObject jsonObject);


}
