import axios from 'axios';
import React from 'react'


export const getSubClassAVG = async (subclassID, zone) => {
    try {
        const response = await axiosInstance.post(`http://localhost:8080/subclass/subClassAVG`, {
            subClassID : subclassID,
            zone : zone
        })
        const data = await response.data;
        console.log(data)

        return data;
    }catch(error)
    {
        throw error;
    }
}
