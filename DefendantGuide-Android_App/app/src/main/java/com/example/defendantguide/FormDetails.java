package com.example.defendantguide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormDetails {

    @Expose
    @SerializedName("Defendant1Name")
    private String Defendant1Name;

    @Expose
    @SerializedName("Defendant2Name")
    private String Defendant2Name;

    @Expose
    @SerializedName("AppealDate")
    private String AppealDate;

    @Expose
    @SerializedName("CourtCostsPaid")
    private String CourtCostsPaid;

    public String getDefendant1Name() {
        return Defendant1Name;
    }

    void setDefendant1Name(String defendant1Name) {
        Defendant1Name = defendant1Name;
    }

    public String getDefendant2Name() {
        return Defendant2Name;
    }

    void setDefendant2Name(String defendant2Name) {
        Defendant2Name = defendant2Name;
    }

    public String getAppealDate() {
        return AppealDate;
    }

    void setAppealDate(String appealDate) {
        AppealDate = appealDate;
    }

    public String getCourtCostsPaid() {
        return CourtCostsPaid;
    }

    void setCourtCostsPaid(String courtCostsPaid) {
        CourtCostsPaid = courtCostsPaid;
    }

}
