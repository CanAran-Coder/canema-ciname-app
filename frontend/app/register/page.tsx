'use client'
import Link from "next/link";
import {useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import {z} from "zod";
import toast from "react-hot-toast";
import { useRouter } from "next/navigation";
import { useAuthStore } from "@/store/useAuthStore";


const registerSchema = z.object({
    email:z.string().email("Invalid email address!"),
    password:z.string().min(6,"Password must be at least 6 characters long!")
})

type RegisterFormData = z.infer<typeof registerSchema>



function Register() {
    const router = useRouter();
    const checkAuth = useAuthStore((state) => state.checkAuth);
    const {register,handleSubmit,formState:{errors,isSubmitting}} = useForm<RegisterFormData>({
        resolver:zodResolver(registerSchema),
        mode:"onSubmit"
    })

    const onSubmit = async (data:RegisterFormData) => {
       try{

            const response = await fetch("http://localhost:8080/api/auth/register",{method:"POST",credentials:"include",headers:{"Content-Type":"application/json"},body:JSON.stringify(data)})
            const responseText = await response.text()
            if(!response.ok){
                toast.error(responseText || "Registration failed!")
                return
            }
            toast.success("Registration successful!")
            await checkAuth()
            router.push("/")


       }catch(err){
        toast.error("Could not connect to server!")
       }
    
    }


    return ( <>
    
        <section className="flex-1 bg-cover bg-center flex justify-center items-center" style={{backgroundImage:"url('/loginBackground.webp')"}}>
                
                <form noValidate onSubmit={handleSubmit(onSubmit)} className="p-3 lg:w-180 lg:h-160 border-2 flex flex-col justify-center items-center border-white  rounded bg-[rgba(0,0,0,0.4)]">
                    <div className="grid grid-cols-[1fr_3fr] gap-x-2 gap-y-2 grid-rows-3">
                        <label className="text-2xl text-white">E-Mail:</label>
                        <input placeholder="E-Mail" {...register("email")} type="email" className="border-2 text-xl border-white rounded text-center text-white"/>
                        {errors.email && <p className="text-red-500 col-span-2 text-center text-xl">{errors.email.message}</p>}
                        <label className="text-2xl text-white">Password:</label>
                        <input placeholder="Password" {...register("password")} type="password" className="border-2 text-xl border-white rounded text-center text-white"/>
                        {errors.password && <p className="text-red-500 col-span-2 text-center text-xl">{errors.password.message}</p>}
                        <button disabled={isSubmitting} type="submit" className="col-span-2 text-black lg:text-xl w-full bg-white rounded hover:cursor-pointer">Register</button>
                        {isSubmitting && <p className="text-white col-span-2 text-center">Registering...</p>}
                        <Link href={"/login"} className="text-center text-white w-full col-span-2 underline text-lg">Do you have an account?</Link>

                    </div>
                    
                </form>
            
            
        </section>
    
    
    </> );
}

export default Register;