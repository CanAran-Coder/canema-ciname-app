'use client'
import SeatSelection from "@/components/SeatSelection";
import { useAuthStore } from "@/store/useAuthStore";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { CiClock2 } from "react-icons/ci";

interface ShowTime{
    id:number,
    startTime:string,
    price:number,
    hallName:string
}


interface MovieData {
    title: string;
    description: string;
    imageURL: string;
    durationMinutes: number;
    ShowtimeResponse: ShowTime[];
}

function MovieTimeButton({time,movieData}:{time:string,movieData:MovieData}) {
    const router = useRouter();
    const [light,setLight] = useState<boolean>(false)
    const user = useAuthStore(state => state.user)
    
    async function handleClick(){
        
        if(user){
            setLight(prev => !prev)
        }else{
            router.push('/login');
        }
        

    }

    const newTime = time.substring(11,16)
    return ( 
        <>
            {light ? <SeatSelection date={time} movieData={movieData} setLight={setLight}/> : null}
            <div onClick={()=> handleClick()} className="border-2 h-15 w-40 border-white p-5 rounded bg-black flex gap-2 cursor-pointer hover:scale-[1.1] ease-in-out duration-200 justify-center items-center">
                <CiClock2 color="white" className="text-2xl"/>
                <p className="text-white text-2xl ">{newTime}</p>
            </div>
        
        </>
     );
}

export default MovieTimeButton;