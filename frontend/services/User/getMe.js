import { axiosInstance } from '@/Helpers/FetchHelper';
import { urlServer } from '@/constants';
import axios from 'axios';
import React from 'react'




export const getMe = async () => {
    
    console.log("EMail: " + email)
    try {
        const response = await axiosInstance.get(`${urlServer}user/getMe`)
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
