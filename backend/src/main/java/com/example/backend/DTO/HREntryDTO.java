package com.example.backend.DTO;

import com.example.backend.Classes.MyUser;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public class HREntryDTO {

    private MyUser user;

    private int value;



    public HREntryDTO(MyUser user,int value) {
        this.user = user;
        this.value = value;

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
                ", value=" + value +
                '}';
    }
}
