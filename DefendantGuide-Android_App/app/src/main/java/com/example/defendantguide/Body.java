package com.example.defendantguide;

import com.example.defendantguide.FormDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {

    Record[] records;

    public Record[] getRecords() {
        return records;
    }

    public void setRecords(Record[] records) {
        this.records = records;
    }

    @Expose
    @SerializedName("form")
    private FormDetails form;

    @Expose
    @SerializedName("checklist")
    private CheckList checklist;

    public CheckList getChecklist() {
        return checklist;
    }

    public void setChecklist(CheckList checklist) {
        this.checklist = checklist;
    }

    @Expose
    @SerializedName("to")
    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public FormDetails getForm() {
        return form;
    }

    public void setForm(FormDetails form) {
        this.form = form;
    }


}
