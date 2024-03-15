import { axiosInstance } from '@/Helpers/FetchHelper';
import axios from 'axios';
import React from 'react'


export const getSubClassAVG = async (subclassID, zone) => {
    try {
        const response = await axiosInstance.get("http://localhost:8080/subclass/subClassAVG/1/day")
        let data = [];
        if(response !== undefined)
        {
            data = await response.data;
            console.log(data)

        }
        

        return data;
    }catch(error)
    {
        throw error;
    }
}
