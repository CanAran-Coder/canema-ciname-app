'use client'
import { RxCross2 } from "react-icons/rx";
import { useAuthStore } from "@/store/useAuthStore";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

function SeatSelection({ setLight }: { setLight: any }) {
    const router = useRouter();
    const user = useAuthStore(state => state.user)
    const [selectedSeats, setSelectedSeats] = useState<number[]>([])
    const [paymentPage, setPaymentPage] = useState<boolean>(false)

    useEffect(() => {
        if (!user) {
            router.push("/")
        }
    }, [user])

    useEffect(() => {
        document.body.style.overflow = 'hidden';

        return () => {
            document.body.style.overflow = 'unset';
        };
    }, []);

    const vertical = 10
    const horizontal = 13
    const full = [1, 5, 8, 10]
    function getSeats(vertical: number, horizontal: number, full: number[]) {
        let seats = []
        for (let i = 1; i <= vertical * horizontal; i++) {
            let status = full.includes(i) ? "FULL" : "EMPTY";
            seats.push({ id: i, status: status })
        }
        return seats
    }

    return (
        <>

            <div className="bg-[rgba(0,0,0,0.8)] fixed z-60 inset-0 w-screen h-screen flex justify-center items-center">
                {!paymentPage ? (<>

                    <div className="bg-white rounded w-300 p-5 h-200 flex flex-col customShadow relative">
                        <RxCross2 onClick={() => setLight(false)} className="absolute top-2 right-2  cursor-pointer text-4xl" />
                        <h1 className="text-black text-4xl mt-10 text-center underline">Seats</h1>
                        <div className="flex-1">
                            <div className="informationDiv w-full h-[20%] grid grid-cols-3 relative">
                                <p className="text-2xl text-start flex items-center">Door</p>
                                <p className="text-2xl justify-center items-center flex">Screen</p>
                                <p></p>
                                <button onClick={()=> setPaymentPage(prev => !prev)} className="absolute bottom-0 right-0 border-2 text-xl bg-black hover:brightness-125 cursor-pointer text-white rounded p-2 ">Go To Payment</button>
                            </div>

                            <div style={{
                                gridTemplateColumns: `repeat(${vertical}, minmax(0, 1fr))`,
                                gridTemplateRows: `repeat(${horizontal}, minmax(0, 1fr))`,
                            }} className={`grid  w-full h-[80%]`}>
                                {getSeats(vertical, horizontal, full).map((item) => <p onClick={() => item.status != "FULL" ? selectedSeats.includes(item.id) ? setSelectedSeats(prev => prev.filter(x => x !== item.id)) : setSelectedSeats(prev => [...prev, item.id]) : ""} key={item.id} className={`hover:brightness-125 w-full h-full border-1 ${item.status == "EMPTY" ? (selectedSeats.includes(item.id) ? "bg-purple-500" : "bg-green-400") : "bg-red-500"} flex justify-center items-center font-bold cursor-pointer`}>{item.id}</p>)}
                            </div>

                        </div>

                            

                    </div>
                </>) : <>
                
                
                
                        
                
                
                
                </>}


            </div>

        </>
    );
}

export default SeatSelection;