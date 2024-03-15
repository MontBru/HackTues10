import React, {FC} from "react";
import jwtDecode from 'jwt-decode'
import useJWTStore from "@/Storages/JWTStorage";
import useUserStore from "@/Storages/UserStorage";

const useCheckJWT = () => {
    const {setAuthenticated} = useJWTStore();
    const {setMe} = useUserStore();

    const checkJWT = async() => {
        const jwt = sessionStorage.getItem('jwtAccess');

        if (jwt === null || jwt === undefined) {
            setAuthenticated(false);

            sessionStorage.removeItem('jwtAccess');
            navigate(routes.login);
            return false;
        }

        let decodedToken;

        try {
            if(jwt !== null)
            {
                decodedToken = jwtDecode<DecodedToken>(jwt);
            }
            
        } catch (error) {
            console.error('Error decoding token:', error);
            setAuthenticated(false);

            sessionStorage.removeItem('jwtAccess');
            // navigate login
            return false;
        }

            const currentTime = Math.floor(Date.now() / 1000);

            if (decodedToken.exp < currentTime) {
            setAuthenticated(false);

            sessionStorage.removeItem('jwtAccess');
            //navigate login
            return false;
            }

        const data = await FetchMe();
        setMe(data); 
        setAuthenticated(true);
        return true;
    }
}