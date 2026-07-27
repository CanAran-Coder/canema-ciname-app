'use client'
import { useAuthStore } from "@/store/useAuthStore";
import { useEffect } from "react";

function AuthInitializer() {

    const checkAuth = useAuthStore((state) => state.checkAuth);
    const user = useAuthStore((state) => state.user);
    useEffect(() => {
        checkAuth();
    }, [checkAuth]);

    return null;
}

export default AuthInitializer;