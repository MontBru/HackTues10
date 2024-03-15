
import { axiosInstance } from '@/Helpers/FetchHelper';
import { responseEncoding } from 'axios';
import React from 'react'




const Registration = async (name, email, password, class_num, device_id, classes, role)  => {
    
    console.log(name)
    try{
        const response = await axiosInstance.post('http://localhost:8080/auth/register', {
            username: name,
            email : email,
            password : password,
            device_id : device_id,
            number : class_num,
            classes: classes,
            role: role
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
