import { urlServer } from '@/constants';
import axios from 'axios';
import React from 'react'


export const getAllUsers= async () => {
    try {
        const response = await axiosInstance.post(`${urlServer}user/findAll`)
        const data = await response.data;
        console.log(data)

        return data;
    }catch(error)
    {
        throw error;
    }
}
