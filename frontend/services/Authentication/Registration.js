
import { responseEncoding } from 'axios';
import React from 'react'
import { json } from 'stream/consumers';



const Registration = async ({formData})  => {
    
    
    try{
        const response = await axiosInstance.post('http://localhost:8080/auth/register', {
            username: formData.username,
            email : formData.email,
            password : formData.password,
            device_id : formData.device_id,
            nimber : formData.number
        })
        const data = await response.data
        return data
    }catch(error)
    {
        alert("this email is already in use. Try again with other email")
        throw error;
    }
}

export default Registration
