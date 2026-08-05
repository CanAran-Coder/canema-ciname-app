'use client'
import { RxCross2 } from "react-icons/rx";
import { makePayment } from "@/actions/paymentAction";
import { useAuthStore } from "@/store/useAuthStore";
import { useRouter } from "next/navigation";
import { useActionState, useEffect, useState } from "react";
import Image from "next/image";
import { getHallInfo } from "@/actions/SeatLayout";

interface ShowTime {
    id: number,
    startTime: string,
    price: number,
    hallName: string
}

interface MovieData {
    title: string;
    description: string;
    imageURL: string;
    durationMinutes: number;
    ShowtimeResponse: ShowTime[];
}

function SeatSelection({ date, setLight, movieData }: { setLight: any, movieData: MovieData, date: string }) {
    const router = useRouter();
    const user = useAuthStore(state => state.user);
    const [state, formAction, isPending] = useActionState(makePayment, null);
    const [selectedSeats, setSelectedSeats] = useState<number[]>([]);
    const [paymentPage, setPaymentPage] = useState<boolean>(false);
    const [hallInfo, setHallInfo] = useState<{ seats_per_row: number, total_rows: number } | null>(null);

    // 1. SEANS (SHOWTIME) EŞLEŞTİRME VE GARANTİYE ALMA
    const timeOnly = date?.includes("T") ? date.split("T")[1].slice(0, 5) : date;

    const matchedShowtime = movieData?.ShowtimeResponse?.find(st =>
        st.startTime === date ||
        st.startTime === timeOnly ||
        st.startTime?.includes(timeOnly) ||
        date?.endsWith(st.startTime)
    );

    // Bulamazsa listenin ilk elemanını al, o da yoksa varsayılan 1 ver (Görünmez patlamayı önler)
    const activeShowtime = matchedShowtime || movieData?.ShowtimeResponse?.[0] || { id: 1, price: 0, hallName: "Not Available", startTime: "" };

    useEffect(() => {
        if (!user) {
            router.push("/");
        }
    }, [user, router]);

    useEffect(() => {
        document.body.style.overflow = 'hidden';

        async function fetchHallInfo() {
            if (activeShowtime?.hallName) {
                const response = await getHallInfo(activeShowtime.hallName);
                if (response) {
                    setHallInfo(response);
                }
            }
        }

        fetchHallInfo();

        return () => {
            document.body.style.overflow = 'unset';
        };
    }, [date, movieData]);

    const full = [1, 5, 8, 10];
    const [fdate, time] = date.split("T");
    const formattedDate = `${fdate} ${time ? time.slice(0, 5) : ""}`;

    function getSeats(vertical: number, horizontal: number, full: number[]) {
        let seats = [];
        for (let i = 1; i <= vertical * horizontal; i++) {
            let status = full.includes(i) ? "FULL" : "EMPTY";
            seats.push({ id: i, status: status });
        }
        return seats;
    }

    const calculatedPrice = (activeShowtime.price * selectedSeats.length).toFixed(2);

    return (
        <>
            <div className="bg-[rgba(0,0,0,0.8)] fixed z-60 inset-0 w-screen h-screen flex justify-center items-center">
                {!paymentPage ? (
                    <div className="bg-white rounded w-300 p-5 h-200 flex flex-col customShadow relative">
                        <RxCross2 onClick={() => setLight(false)} className="absolute top-2 right-2 cursor-pointer text-4xl" />
                        <h1 className="text-black text-4xl mt-10 text-center underline">Seats</h1>
                        <div className="flex-1">
                            <div className="informationDiv w-full h-[20%] grid grid-cols-3 relative">
                                <p className="text-2xl text-start flex items-center">Door</p>
                                <p className="text-2xl justify-center items-center flex">Screen</p>
                                <p></p>
                                <button 
                                    onClick={() => setPaymentPage(true)} 
                                    className="absolute bottom-0 right-0 border-2 text-xl bg-black hover:brightness-125 cursor-pointer text-white rounded p-2"
                                >
                                    Go To Payment
                                </button>
                            </div>

                            <div style={{
                                gridTemplateColumns: `repeat(${hallInfo?.seats_per_row || 15}, minmax(0, 1fr))`,
                                gridTemplateRows: `repeat(${hallInfo?.total_rows || 10}, minmax(0, 1fr))`,
                            }} className="grid w-full h-[80%]">
                                {getSeats(hallInfo?.total_rows || 10, hallInfo?.seats_per_row || 15, full).map((item) => (
                                    <p 
                                        key={item.id} 
                                        onClick={() => item.status !== "FULL" ? selectedSeats.includes(item.id) ? setSelectedSeats(prev => prev.filter(x => x !== item.id)) : setSelectedSeats(prev => [...prev, item.id]) : ""} 
                                        className={`hover:brightness-125 w-full h-full border-1 ${item.status === "EMPTY" ? (selectedSeats.includes(item.id) ? "bg-purple-500" : "bg-green-400") : "bg-red-500"} flex justify-center items-center font-bold cursor-pointer`}
                                    >
                                        {item.id}
                                    </p>
                                ))}
                            </div>
                        </div>
                    </div>
                ) : (
                    <form action={formAction} className="bg-white w-300 h-130 p-2 rounded customShadow grid grid-rows-2 gap-y-2 relative">
                        <RxCross2 onClick={() => setLight(false)} className="absolute top-2 right-2 text-2xl cursor-pointer" />

                        {/* GARANTİLİ GİZLİ GİRDİLER */}
                        <input type="hidden" name="seatNumbers" value={JSON.stringify(selectedSeats)} />
                        <input type="hidden" name="showTimeId" value={activeShowtime.id} />
                        <input type="hidden" name="hallName" value={activeShowtime.hallName} />
                        <input type="hidden" name="userMail" value={user?.email || "bilet@canema.com"} />

                        <div className="w-full h-full grid grid-cols-[1fr_3fr] gap-x-10 p-2 border-b-2">
                            <div className="h-full w-full relative">
                                <Image src={movieData.imageURL} className="rounded customShadow" fill alt={movieData.title} />
                            </div>

                            <div className="grid grid-rows-5 grid-cols-[auto_1fr] w-full h-full">
                                <label className="text-2xl">Title:</label>
                                <label className="text-2xl">{movieData.title}</label>
                                <label className="text-2xl">Date:</label>
                                <label className="text-2xl">{formattedDate}</label>
                                <label className="text-2xl">Hall Name:</label>
                                <label className="text-2xl">{activeShowtime.hallName}</label>
                                <label className="text-2xl">Seats:</label>
                                <label className="text-2xl gap-x-2 flex">{selectedSeats.map((item, index) => <p key={index}>{item}</p>)}</label>
                                <label className="text-2xl">Price:</label>
                                <label className="text-2xl">{calculatedPrice}$</label>
                            </div>
                        </div>

                        <div className="grid grid-cols-[1fr_3fr] content-end gap-2 w-full h-full">
                            <label className="text-black text-2xl">Name On Card:</label>
                            <input className="text-center border-2 text-xl text-black" name="holderName" defaultValue="Ahmet Yilmaz" />
                            <label className="text-black text-2xl">Card Number:</label>
                            <input className="text-center border-2 text-xl text-black" name="cardNumber" defaultValue="5890040000000016" />
                            <label className="text-black text-2xl">Date:</label>
                            <div className="flex gap-x-2">
                                <input className="text-center border-2 text-xl text-black w-full" placeholder="Month" name="cardMonth" defaultValue="05" />
                                <input className="text-center border-2 text-xl text-black w-full" placeholder="Year" name="cardYear" defaultValue="2030" />
                            </div>
                            <label className="text-black text-2xl">CVV:</label>
                            <input className="text-center border-2 text-xl text-black" name="cardCVV" defaultValue="123" />
                            
                            <button 
                                type="submit" 
                                disabled={isPending} 
                                className="col-span-2 bg-black text-white rounded h-10 cursor-pointer hover:scale-[1.01] duration-250 ease-in-out text-2xl"
                            >
                                {isPending ? "Processing..." : `Pay: ${calculatedPrice}$`}
                            </button>
                            {isPending && <p className="col-span-2 text-center text-2xl">Processing...</p>}
                        </div>
                    </form>
                )}
            </div>
        </>
    );
}

export default SeatSelection;