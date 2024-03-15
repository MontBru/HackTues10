import axios from 'axios';
import React from 'react'


export const getSubClassAVG = async ({subclassID} ) => {
    try {
        const response = await axiosInstance.post(`http://localhost:8080/subclass/subClassAVG/${subject_id}`)
        const data = await response.data;
        console.log(data)

        return data;
    }catch(error)
    {
        throw error;
    }
}
