import { axiosInstance } from '@/Helpers/FetchHelper';
import { urlServer } from '@/constants';


export const getStudentEvaluation = async (userId, flag) => {


    try {
        const response = await axiosInstance.get(`${urlServer}user/getEvaluation/${userId}/${flag}`)
        if(response !== undefined)
        {
            const data = await response.data;
            console.log(data)
            return data;
        }




    }catch(error)
    {
        throw error;
    }
}