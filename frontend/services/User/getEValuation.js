import { urlServer } from '@/constants';
import axios from 'axios';
import React from 'react'



export const getSubClassAVG = async ({subclassID, time} ) => {
    try {
        const response = await axiosInstance.post(`${urlServer}user/getEvaluation/${id}/${time}`)
        const data = await response.data;
        console.log(data)

        return data;
    }catch(error)
    {
        throw error;
    }
}
