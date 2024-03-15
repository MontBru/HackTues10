import axios from 'axios';
import React from 'react'


export const Authentication = async ({email, password} ) => {
    

    try {
        const response = await axiosInstance.post('http://localhost:8080/auth/authentication',{
            email: email,
            password: password
        })
        const data = await response.data;
        console.log(data)

        return data;
    }catch(error)
    {
        throw error;
    }
}
