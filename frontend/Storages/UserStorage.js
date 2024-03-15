import { create } from 'zustand';




const useUserStore = create((set) => ({
    me: { 
        username: "",
        email: "",
        number: 0,
        hrEntries: [],
        device_id: ""
    },
    setMe: (myUser) => set((state) => { 
        return { ...state,me: myUser }
    }
    ),
}));

export default useUserStore;