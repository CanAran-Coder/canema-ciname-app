import { useEffect } from "react";



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

    useEffect(() => {
            document.body.style.overflow = 'hidden';
    
            return () => {
                document.body.style.overflow = 'unset';
            };
        }, []);



    return ( 
        <>
        
            <div className="bg-[rgba(0,0,0,0.7)] z-50 inset-0 fixed w-screen h-screen flex  flex-col justify-center items-center">
                
                <form className="border-2 gap-5 border-white w-200 h-150 rounded p-5 grid grid-rows-[1fr_2fr]">

                    <div className="flex justify-center items-center"><h1 className="text-white text-4xl border-b-2">Add Movie</h1></div>                        
                        

                    <div className="grid grid-rows-3 grid-cols-2">
                        
                    </div>


                    
                </form>

                
            </div>        
        
        </>
     );
}

export default FilmAdd;