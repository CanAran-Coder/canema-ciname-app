'use client'
import { CiClock2 } from "react-icons/ci";
function MovieTimeButton({time}:{time:string}) {
    const newTime = time.substring(11,16)
    return ( 
        <>
        
            <div className="border-2 h-15 w-40 border-white p-5 rounded bg-black flex gap-2 cursor-pointer hover:scale-[1.1] ease-in-out duration-200">
                <CiClock2 color="white" className="text-2xl"/>
                <p className="text-white">{newTime}</p>
            </div>
        
        </>
     );
}

export default MovieTimeButton;