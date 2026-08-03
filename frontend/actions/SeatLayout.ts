'use server'


export async function getHallInfo(hallName:string){
    const response = await fetch("http://localhost:8080/api/hall/getHall",{method:"POST",headers:{"Content-Type": "application/json"},body:JSON.stringify({hallName:hallName})})
    const data = await response.json()
    console.log(data)
    return {"seats_per_row": data.seats_per_row, "total_rows": data.total_rows}
}