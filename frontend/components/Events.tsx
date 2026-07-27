'use client'
import Event_Movie_Item from "@/UI/Event_Movie_Item";
import { useEffect, useState } from "react";
import { FaCalendarDays } from "react-icons/fa6";
function Events() {

    const [days, setDays] = useState<string[]>([]);
    const [selectedDay,setSelectedDay] = useState<string>(days[0])
    const [movies,setMovies] = useState<MovieData[]>();

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

    useEffect(() => {
        const next14Days = [];
        const today = new Date();

        for (let i = 0; i < 14; i++) {
            const d = new Date(today);
            d.setDate(today.getDate() + i);

            const year = d.getFullYear();
            const month = String(d.getMonth() + 1).padStart(2, '0');
            const day = String(d.getDate()).padStart(2, '0');

            next14Days.push(`${year}-${month}-${day}`);
        }

        setDays(next14Days);
        setSelectedDay(next14Days[0])
    }, []);
    useEffect(()=>{

        if(selectedDay == undefined) return;
        getMoviesByDate(selectedDay).then(data => setMovies(data))
        
   
    },[selectedDay])


  

    async function getMoviesByDate(date: string) {
        const res = await fetch(`http://localhost:8080/api/movies/byDate/${date}`);
        const data = await res.json();
        return data;
    }

    return (<>


        <div className="grid grid-cols-[0.75fr_2fr] w-full h-[120vh] bg-black">
            <div className="p-6 ">
                <div className="w-full h-full bg-white customShadow rounded grid grid-rows-14">
                    {days.map((item) => <button key={item} onClick={() => setSelectedDay(item)} className="text-xl flex justify-start items-center pl-5 gap-2 cursor-pointer hover:bg-gray-100 rounded text-2xl"><FaCalendarDays className="text-2xl" />{new Date(`${item}T00:00:00`).toLocaleDateString('tr-TR', { day: 'numeric', month: 'long', weekday: 'long' })}</button>)}
                </div>
            </div>
            <div className="grid grid-rows-3 border-t border-white p-2">


                {movies?.map((item)=>(<Event_Movie_Item key={item.id} data={item} />))}



            </div>
        </div>

    </>);
}

export default Events;