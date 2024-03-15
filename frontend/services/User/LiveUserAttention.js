import { axiosInstance } from '@/Helpers/FetchHelper';
import { urlServer } from '@/constants';
import axios from 'axios';
import React from 'react'




export const getLiveUserAttention = async (klas, grade) => {
    
    try {
        const response = await axiosInstance.get(`${urlServer}subclass/studentsWithLostAttention/${klas}/${grade}`)
        
        if(response !== undefined)
        {
            const data = await response.data;
            console.log(data)
            return data;
        }
        
        

        
    }catch(error)
    {
        throw error;
    }
}
