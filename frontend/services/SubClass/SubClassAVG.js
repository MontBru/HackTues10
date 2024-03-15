import { axiosInstance } from '@/Helpers/FetchHelper';
import { urlServer } from '@/constants';
import axios from 'axios';
import React from 'react'



export const getSubClassAVG = async (subclassID, zone) => {
    try {
        const [klas, grade] = subclassID.split(" ")
        console.log(klas, grade)
        const url = `${urlServer}subclass/subClassAVG/${klas}/${grade}/${zone}`;
        const response = await axiosInstance.get(url)
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
