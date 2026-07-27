'use client'
import Link from "next/link";

function LoginButton() {
    return ( 
        <>

            <Link href={"/login"} className="border-b-2 px-1 lg:text-xl  hover:scale-[1.05] duration-200 ease-in-out ">Login</Link>
        
        </>
     );
}

export default LoginButton;