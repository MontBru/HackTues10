import { axiosInstance } from '@/Helpers/FetchHelper';
import { urlServer } from '@/constants';
import axios from 'axios';
import React from 'react'




export const Authentication = async (email, password ) => {
    
    console.log("EMail: " + email)
    try {
        const response = await axiosInstance.post(`${urlServer}auth/authentication`,{
            email: email,
            password: password
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
