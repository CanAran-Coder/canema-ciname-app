import { useEffect, useState } from "react";
import toast from "react-hot-toast";
function FilmDeleteSure({setLight,filmName,filmId}:{setLight:React.Dispatch<React.SetStateAction<boolean>>, filmName:string, filmId:number}) {
    const [loading,setLoading] = useState<boolean>(false);
    async function handleDeleteFilm(id:number){
        setLoading(true)
        try{
            const response = await fetch(`http://localhost:8080/api/movies/deleteMovie/${filmId}`, {method:"DELETE",body:JSON.stringify({filmId:id}),credentials:"include",headers:{"Content-Type":"application/json"}})
            const responseText = await response.text()
            if(!response.ok){
                toast.error(responseText || "Error while deleting film!")
                setLoading(false)
                setLight(false)
                return
            }
            toast.success(responseText || "Film Deleted Successfully!")
            setLoading(false)
            setLight(false)
            
            
            

        }catch(err){
            toast.error("Error while deleting film: "+err)
            setLoading(false)
            setLight(false)
        }
        
       
    }



    useEffect(() => {
        document.body.style.overflow = 'hidden';

        return () => {
            document.body.style.overflow = 'unset';
        };
    }, []);
    return ( <>
    
        <div className="w-screen h-screen fixed inset-0 bg-[rgba(0,0,0,0.7)] z-50 flex flex-col justify-center items-center">
            <h1 className="text-white text-3xl">Are you sure you want to delete <span className="border-b border-white">{filmName}</span>?</h1>
            <div className="w-full  flex justify-center items-center gap-5">
                <button className="bg-red-500 text-white cursor-pointer px-4 py-2  text-2xl rounded hover:bg-red-600" onClick={() => setLight(false)}>No</button>
                <button className="bg-green-500 text-white cursor-pointer text-2xl px-4 py-2 rounded hover:bg-green-600" onClick={() => handleDeleteFilm(filmId)}>{loading ? "loading..." : "Yes"}</button>
            </div>
        </div>

    </> );
}

export default FilmDeleteSure;