'use client'
import { PiTelevisionSimpleFill } from "react-icons/pi";
import style from "./Navbar.module.css"
import LoginButton from "@/UI/LoginButton";
import Link from "next/link";
import { useAuthStore } from "@/store/useAuthStore";
import { useState } from "react";
import FilmAdd from "./FilmAdd";
function Navbar() {

    const [light,setLight] = useState<boolean>(false)
    const [addLoading,setAddLoading] = useState<boolean>(false)



    const user = useAuthStore((state) => state.user);
    const loading = useAuthStore((state) => state.loading);
    const logout = useAuthStore((state) => state.logout);



    async function handleAddMovie(){
        setLight(prev => !prev)
    }
    
    return ( 
        <>
            {light? <FilmAdd loading={loading} setLight={setLight} setLoading={setAddLoading}/> : null}
            <nav className="grid grid-cols-[1fr_3fr_1fr] h-[8vh] customShadow ">
                


            <div className="flex justify-center items-center gap-4 h-full">
                <h1 className={`text-center font-bold lg:text-4xl ${style.brandTitle} `}>CANEMA</h1>
                <PiTelevisionSimpleFill className="text-5xl"/>
            </div>
            <div className="h-full justify-around px-20 items-center flex">
                <Link href={"/"} className="border-b-2 px-1 lg:text-xl  hover:scale-[1.05] duration-200 ease-in-out ">Menu</Link>
                
                
            </div>
            <div className="h-full flex justify-center items-center">
                {loading ? <p className="text-white text-xl">Loading...</p> : user ? <div className="flex gap-4 items-center"><p className="text-black text-xl">{user.email.split("@")[0]}</p>{user.role == "ROLE_ADMIN" ? <button onClick={handleAddMovie} className="cursor-pointer border-b-2 px-1 lg:text-xl  hover:scale-[1.05] duration-200 ease-in-out ">Add Movie</button> : null}<button onClick={logout} className="cursor-pointer border-b-2 px-1 lg:text-xl  hover:scale-[1.05] duration-200 ease-in-out ">Logout</button></div> : <LoginButton/>}

            </div>
            </nav>

        </>
     );
}

export default Navbar;