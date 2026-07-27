import toast from "react-hot-toast";
import {create} from "zustand";

interface User{
    email:string
    role:string
}

interface AuthState {
  user: User | null;
  loading: boolean;
  checkAuth: () => Promise<void>;
  logout: () => Promise<void>;
}

export const useAuthStore = create<AuthState>((set) => ({
    user:null,
    loading:true,

    checkAuth: async ()=>{
        try{
            const response = await fetch("http://localhost:8080/api/auth/me",{method:"GET",credentials:"include"})
            if(response.ok){
                const userData = await response.json()
                set({user:userData,loading:false})

            }else{
                set({user:null,loading:false})

            }
        }catch(err){
            set({user:null,loading:false})
        }
    },

    logout: async ()=>{
        try{
           const response  = await fetch("http://localhost:8080/api/auth/logout",{method:"POST",credentials:"include"})
           const responseText = await response.text()
           if(response.ok){
                toast.success(responseText || "Logout successful!")
                set({user:null})
           }else{
                toast.error(responseText || "Logout failed!")
           }
            
        }catch(err){
            console.error("Logout error:", err)
        }

    }
}))