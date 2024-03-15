import axios, { HttpStatusCode } from 'axios'
import { useNavigate } from 'react-router-dom';
import { routes } from '../constants';
import useJWTStore from '../Storages/JWTStorage';
import React, { FC, PropsWithChildren, useEffect } from 'react'

export const axiosInstance = axios.create();





axiosInstance.interceptors.request.use(
    config => {

        const token = sessionStorage.getItem("jwtAccess")

        if(token !== null && token !== undefined)
        {

            config.headers['Authorization'] = 'Bearer ' + token
        }



        return config;
    },
    error => {

        return Promise.reject(error);
    }
);

axiosInstance.interceptors.response.use( 
    async (response) => {


      return response
    },

    async (error) => {

      console.log("alo da ")
      console.log("An error occurred:", error);

      // Check if the error is due to CORS
      if (error.response && error.response.status === HttpStatusCode.Forbidden) {
        console.log("CORS issue detected. Reloading the page...");
        window.location.reload();
      }

      // Handle unauthorized and forbidden errors
      if (error.response && (error.response.status === HttpStatusCode.Unauthorized || error.response.status === HttpStatusCode.Forbidden)) {
        console.log("Unauthorized or Forbidden. Reloading the page...");
        window.location.reload();
      }

      // Handle other error cases if needed

      // Returning a Pzromise.reject to propagate the error
      return Promise.reject(error);


    }
  );
