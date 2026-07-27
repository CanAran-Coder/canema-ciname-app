import AboutUs from "@/components/AboutUs";
import Contact from "@/components/Contact";
import Events from "@/components/Events";
import HomeSlider from "@/components/HomeSlider";
import Navbar from "@/components/Navbar";


export default function Home() {
  return (
    <>
      <HomeSlider/>
      <Events/>
      <AboutUs/>
      <Contact/>
    </>
  )
}
