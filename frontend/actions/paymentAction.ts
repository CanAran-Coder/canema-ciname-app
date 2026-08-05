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

        console.log("➡️ [Next.js] Backend'e yollanan veri:", rawData);

        const response = await fetch("http://127.0.0.1:8080/api/payment", {
            method: "POST",
            body: JSON.stringify(rawData),
            headers: { 
                "Content-Type": "application/json" 
            },
            cache: 'no-store'
        });

      

        const data = await response.json();
        return { success: true, message: "Ödeme başarılı!" };

    } catch (error: any) {
        console.error("💥 [Next.js] Exception:", error);
        return { success: false };
    }
}