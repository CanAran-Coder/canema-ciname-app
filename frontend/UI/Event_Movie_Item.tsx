import { RxCross1 } from "react-icons/rx";
import Image from 'next/image';
import MovieTimeButton from './MovieTimeButton';
import { useState } from "react";
import FilmDeleteSure from "@/components/FilmDeleteSure";

interface MovieData{
    id:number
    title:string
    description:string
    durationMinutes:number
    hallName:string
    imageURL:string
    price:number
    totalSeats:number
    showTime:string[]

}

function Event_Movie_Item({data}:{data:MovieData}) {

    const [light,setLight] = useState(false);
    async function handleDeleteFilm(id:number){
        
        
    }

    return ( <>
        {light && <FilmDeleteSure setLight={setLight} filmName={data.title} filmId={data.id}/>}
        
        <div className='grid grid-cols-[1fr_3fr] border-b border-white pb-4 relative'>
            <div onClick={() => setLight(true)} className='absolute top-0 right-0 p-2 rounded bg-red-500 cursor-pointer ease-in-out hover:scale-[1.1] duration-200'>
                <RxCross1 className='text-white text-xl font-bold size-6 ' />
            </div>

            <div className='flex flex-col  items-center gap-2'>
                <h1 className='text-white text-2xl border-b border-white'>{data.title}</h1>
                <div className='w-full relative customShadow h-full rounded '><Image fill src={data.imageURL} className="customShadow rounded object-cover" alt={data.title}></Image></div>
            </div>


            <div className='w-full h-full grid grid-rows-[4fr_1fr] place-items-center'>
                <p className='text-white text-xl text-center'>{data.description}</p>
                <div className='flex gap-5 justify-center items-center'>
                    {data.showTime.map((item)=>(<MovieTimeButton key={item} time={item} movieData={data}/>))}
                </div>
                
            </div>

        </div>

    
    
    </> );
}

export default Event_Movie_Item;