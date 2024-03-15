package com.example.backend.DTO;

import com.example.backend.Classes.MyUser;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public class HREntryDTO {

    private String id;
    private int value;



    public HREntryDTO(String id,int value) {
        this.id = id;
        this.value = value;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "HREntryDTO{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }
}
