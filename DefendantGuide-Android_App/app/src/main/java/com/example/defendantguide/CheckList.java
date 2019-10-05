package com.example.defendantguide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckList {
    @Expose
    @SerializedName("Evidence")
    private String[] evidence;

    @Expose
    @SerializedName("Witness")
    private String[] witness;


    CheckList(){
        evidence = new String[3];
        witness = new String[3];
    }
    public String[] getEvidence() {
        return evidence;
    }

    public void setEvidence(String[] evidence) {
        this.evidence = evidence;
    }

    public String[] getWitness() {
        return witness;
    }

    public void setWitness(String[] witness) {
        this.witness = witness;
    }
}
