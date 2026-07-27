'use client'
import { TbAirConditioningDisabled } from "react-icons/tb";
import { BsSoundwave } from "react-icons/bs";
import { IoIosVolumeOff } from "react-icons/io";
import { FaFeatherAlt } from "react-icons/fa";
function AboutUs() {
    return ( <>

        <div  className="w-full h-[30vh] grid grid-cols-4">
            
            <div className=" w-full h-full flex flex-row justify-center items-center customShadow text-2xl gap-2"><p>A/C </p><TbAirConditioningDisabled className="text-4xl"/></div>
            <div className="w-full h-full flex justify-center items-center customShadow text-2xl gap-2"> <p>Premium Sound System </p><BsSoundwave className="text-4xl"/></div>
            <div className="w-full h-full flex justify-center items-center customShadow text-2xl gap-2"> <p>Superior Sound Insulation </p><IoIosVolumeOff className="text-4xl"/></div>
            <div className="w-full h-full flex justify-center items-center customShadow text-2xl gap-2"> <p>Exceptional Comfort  </p><FaFeatherAlt className="text-4xl"/></div>

        </div>
    
    </> );
}

export default AboutUs;