'use client'

interface MovieData{

    id:number
    title:string
    showTime:string
    duration:string
    totalSeats:number
    movieImage:string
}
function SliderItem({data}:{data:MovieData}) {
    
    return ( 

        <>
        
            <div 
            style={{backgroundImage : `url(${data.movieImage})`}}
            className={`w-full h-full bg-cover bg-center flex justify-center items-center`}>
                
                <div className="bg-[rgba(0,0,0,0.4)]">   
                    <h1>{data.title}</h1>
                    
                </div>


            </div>
        
        </>
     );
}

export default SliderItem;