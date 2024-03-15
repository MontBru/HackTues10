import { create } from 'zustand';




const useJWTStore = create((set) => ({
    isAuthenticated: false,
    setIsAuthenticated: (authenticated) => set((state) => { 
        return { ...state,isAuthenticated: authenticated }
    }
    ),
}));

export default useJWTStore;