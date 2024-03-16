import { axiosInstance } from '@/Helpers/FetchHelper';
import { urlServer } from '@/constants';
import axios from 'axios';
import React from 'react'




export const updateHrEntry = async (value) => {
    
    try {
        const response = await axiosInstance.get(`${urlServer}/hrEntry/studentsWithLostAttention`, {
            klas:klas,
            grade: grade
        })

        if(response !== undefined)
        {
            const data = await response.data;
            console.log(data)
        }
        
        

        return data;
    }catch(error)
    {
        throw error;
    }
}
