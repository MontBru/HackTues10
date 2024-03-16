import { axiosInstance } from '@/Helpers/FetchHelper';
import { urlServer } from '@/constants';



export const getStudentsFromClass = async (subclassID) => {
    try {
        console.log("KLAS" + subclassID)
        const [klas, grade] = subclassID.split(" ")
        console.log(klas, grade)
        const url = `${urlServer}subclass/getStudentsFromClass/${klas}/${grade}`;
        const response = await axiosInstance.get(url)
        let data = [];
        if(response !== undefined)
        {
            data = await response.data;
            console.log(data)

        }

        return data;
    }catch(error)
    {
        throw error;
    }
}
