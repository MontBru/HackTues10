import { create } from 'zustand';




const useJWTStore = create<Store>((set) => ({
    isAuthenticated: false,
    setIsAuthenticated: (authenticated) => set((state) => { 
        return { ...state,isAuthenticated: authenticated }
    }
    ),
}));

export default useJWTStore;