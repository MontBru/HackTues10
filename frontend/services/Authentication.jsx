import { urlServer } from '@/constants';
import axios from 'axios';

const Authentication = async ({ email, password }) => {
    try {
        const response = await axios.post(urlServer + 'auth/authentication', {
            email: email,
            password: password
        });
        const data = await response.data;
        console.log(data);
        return data;
    } catch (error) {
        throw error;
    }
};

export default Authentication;
