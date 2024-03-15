import axios from 'axios';
import React from 'react'


export const getSubjectHREntries = async ({subject_id, date} ) => {
    try {
        const response = await axiosInstance.post(`http://localhost:8080/subject/getAllHREtries/${subject_id}/${date}`)
        const data = await response.data;
        console.log(data)

        return data;
    }catch(error)
    {
        throw error;
    }
}
