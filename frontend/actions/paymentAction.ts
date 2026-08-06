'use server'

export async function makePayment(prevState: any, formData: FormData) {
    try {
        const seatNumbersRaw = formData.get("seatNumbers") as string;
        const seatNumbers = seatNumbersRaw ? JSON.parse(seatNumbersRaw) : [];

        
        let parsedShowTimeId = Number(formData.get("showTimeId"));
        if (!parsedShowTimeId || isNaN(parsedShowTimeId)) {
            parsedShowTimeId = 1; 
        }


        const rawData = {
            cardNumber: formData.get("cardNumber"),
            cardMonth: formData.get("cardMonth"),
            cardYear: formData.get("cardYear"),
            cardCVV: formData.get("cardCVV"),
            holderName: formData.get("holderName"),

            seatNumbers: seatNumbers,
            showTimeId: parsedShowTimeId, 

            hallName: formData.get("hallName") ,
            userMail: formData.get("userMail")
        };
        console.log("Raw Data to be sent:", rawData);

        

        const response = await fetch("http://localhost:8080/api/payment/normalPayment", {
            method: "POST",
            body: JSON.stringify(rawData),
            headers: { 
                "Content-Type": "application/json" 
            },
            cache: 'no-store'
        });

        const responseData = await response.json();
        console.log("Response from server:", responseData);

        if (!response.ok) {
            throw new Error(`Server responded with status ${response.status}: ${responseData.message || 'Unknown error'}`);
        }

      

    

    } catch (error: any) {
        console.error("[Next.js] Exception:", error);
    }
}