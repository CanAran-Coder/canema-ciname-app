'use client'
import MovieTimeButton from "@/UI/MovieTimeButton";
import { useEffect, useState } from "react";



interface MovieData {
  title: string;
  showTime: string[];
  description: string;
  imageURL: string;
  durationMinutes: number;
  hallName: string;
  totalSeats: number;
  price: number;
}


function FilmAdd({setLight,setLoading,loading}:{setLight:any,loading:boolean,setLoading:any}) {
    const [showTimes,setShowTimes] = useState<string[]>([])
    const [currentTime,setCurrentTime] = useState<string>("")
    
   

    async function handleSubmit(e:React.FormEvent<HTMLFormElement>){
            e.preventDefault();
            const formData = new FormData(e.currentTarget)
            const sendFormData = new FormData()
            sendFormData.append("file",formData.get("movieImage") as File)
            sendFormData.append("upload_preset","canema_preset")
            sendFormData.append("folder","movies")
            try{

                const response = await fetch("https://api.cloudinary.com/v1_1/duxfdxiup/image/upload",{method:"POST",body:sendFormData})
                const data = await response.json();
                console.log(data.secure_url)

            }
            catch(err){

            }
    }   


    useEffect(() => {
            document.body.style.overflow = 'hidden';
    
            return () => {
                document.body.style.overflow = 'unset';
            };
        }, []);



    return ( 
        <>
        
            <div className="bg-[rgba(0,0,0,0.7)] z-50 inset-0 fixed w-screen h-screen flex  flex-col justify-center items-center">
                
                <form onSubmit={handleSubmit} className="border-2 flex flex-col border-white rounded w-200 h-200 p-10">
                     <h1 className="text-white text-4xl text-center border-b-2" >Movie Add</h1>
                     <div className="w-full h-125 grid grid-cols-[1fr_4fr] content-start p-2 items-center gap-x-1 gap-y-2">
                        
                            <label className="text-white text-2xl w-full  text-start">Title:</label>
                            <input className="border-2 border-white rounded w-full h-8 text-center text-white text-xl" name="title"/>
                            <label className="text-white text-2xl w-full  text-start">Duration(Min):</label>
                            <input type="number" className="border-2 border-white rounded w-full h-8 text-center text-white text-xl" name="duration"/>
                            <label className="text-white text-2xl w-full  text-start">Hall:</label>
                            <input className="border-2 border-white rounded w-full h-8 text-center text-white text-xl" name="hall"/>
                            <label className="text-white text-2xl w-full  text-start">Total Seats:</label>
                            <input type="number" className="border-2 border-white rounded w-full h-8 text-center text-white text-xl" name="totalSeats"/>
                            <label className="text-white text-2xl w-full  text-start">Price:</label>
                            <input type="number" className="border-2 border-white rounded w-full h-8 text-center text-white text-xl" name="price"/>
                            <label className="text-white text-2xl w-full  text-start">Image:</label>
                            <input type="file" className="border-2 border-white rounded w-full h-8 text-center text-white text-xl" name="movieImage"/>
                            <label className="text-white text-2xl w-full  text-start">Show Time:</label>
                            <input value={currentTime} onChange={(e)=>setCurrentTime(e.target.value )} type="datetime-local" className="border-2 border-white rounded w-full h-8 text-center text-white text-xl" name="showTime"/>
                            <button type="button" onClick={()=> setShowTimes(prev => [...prev, currentTime])} className="text-white rounded border-2 border-white col-span-2 cursor-pointer hover:scale-[1.03] hover:bg-white hover:text-black ease-in-out duration-200 text-xl">Add Time</button>
                            <label className="text-white text-2xl w-full  text-start">Description:</label>
                            <textarea rows={4} className="border-2 border-white text-white resize-none rounded"></textarea>
                            <button className="text-white rounded border-2 border-white col-span-2 cursor-pointer hover:scale-[1.03] hover:bg-white hover:text-black ease-in-out duration-200 text-xl">Add Movie</button>
                        
                     </div>
                     <div className="grid grid-cols-[1fr_4fr] w-full h-full content-start">
                        <label className="text-white text-2xl">Times:</label>
                        <div className="w-full h-full grid grid-cols-3 gap-2 overflow-y-auto min-h-50 p-2 max-h-50">
                            {showTimes.map((item,index)=> <MovieTimeButton key={index} time={item} />)}
                        </div>
                        
                     </div>
                </form>
                
            </div>        
        
        </>
     );
}

export default FilmAdd;