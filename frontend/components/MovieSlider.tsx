'use client';


import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Autoplay } from 'swiper/modules';

import 'swiper/css';
import 'swiper/css/navigation';

interface MovieData {
  id: number;
  title: string;
  description: string;
  imageURL: string;
  durationMinutes: number;
  hallName: string;
  totalSeats: number;
  price: number;
}



export default function MovieSlider({ movies = [] }: { movies: MovieData[] }) {
  if (!Array.isArray(movies) || movies.length === 0) return null;
  return (
    <div className="w-full h-full relative">
      <Swiper
        modules={[Navigation, Autoplay]}
        spaceBetween={0}
        slidesPerView={1}
        loop={movies.length > 1}
        autoplay={{
          delay: 5000,
          disableOnInteraction: false,
        }}
        navigation={true}
        className="w-full h-full [&_.swiper-slide]:h-full [&_.swiper-slide]:w-full"
      >
        {movies.map((movie: MovieData) => (
          <SwiperSlide key={movie.id}>
            <div className="relative w-full h-full flex items-end justify-start overflow-hidden">
              
             
              <img
                src={movie.imageURL}
                alt={movie.title}
                className="absolute inset-0 w-full h-full object-cover z-0"
              />

            
              <div className="absolute inset-0 bg-gradient-to-t from-black via-black/50 to-transparent z-10" />

              
              <div className="relative z-20 p-8 md:p-16 max-w-5xl text-left text-white">
                
               
                <h2 className="text-5xl md:text-7xl font-extrabold mb-4 drop-shadow-lg tracking-tight">
                  {movie.title}
                </h2>

               
                <p className="text-gray-200 text-lg md:text-xl mb-6 line-clamp-3 leading-relaxed max-w-2xl">
                  {movie.description}
                </p>

                

                <div className="flex flex-wrap items-center gap-4 md:gap-6 text-base md:text-lg font-medium text-gray-200">
                  <span className='text-white font-bold'>{movie.durationMinutes} Min</span>
                
                  <span className="text-white font-bold">
                    Today
                  </span>

                  
                  
                </div>

              </div>

            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}