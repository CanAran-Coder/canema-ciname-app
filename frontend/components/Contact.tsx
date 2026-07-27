import { MdEmail } from "react-icons/md";
import { FaPhone } from "react-icons/fa";
function Contact() {
    return ( <>
    
        
        <div className="w-full h-[60vh] bg-black flex flex-col">
            <h1 className="text-center mt-5 font-bold text-white text-6xl">Contact Us</h1>
            <div className="flex-1 grid grid-cols-2">
                <div className="flex flex-col justify-center items-center  text-white w-full h-full">
                    <p className="flex justify-center items-center gap-2 text-2xl"><MdEmail/>E-Mail:canema@gmail.com</p>
                    <p className="flex justify-center items-center gap-2 text-2xl"><FaPhone/>Phone Number:5555555555</p>
                    
                </div>
                <div className="w-full h-full flex justify-center items-center ">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2993.4875129311545!2d27.3482946!3d41.38521794999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14b4a35b3b230fc9%3A0x1f138c997ad2e384!2s39%20Burda%20Avm!5e0!3m2!1sen!2str!4v1784823161042!5m2!1sen!2str" width="400" height="400" style={{border:"0"}} className="rounded customShadow" allowFullScreen={false} loading="lazy" referrerPolicy="strict-origin-when-cross-origin"></iframe>
                </div>
            </div>
        </div>

    
    </> );
}

export default Contact;