package com.example.defendantguide;

import java.util.ArrayList;
import java.util.List;

public class Transcript {
    static List<Record> record;

    public static List<Record> getRecord() {
        return record;
    }

    public static void setRecord(List<Record> record) {
        Transcript.record = record;
    }

    public static void addRecord(Record record){
        if(Transcript.record == null){
            Transcript.record = new ArrayList<Record>();
        }
        Transcript.record.add(record);
    }
    public static void clearList(){
        Transcript.record = null;
    }
}
