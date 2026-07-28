import MovieSlider from "./MovieSlider";
async function HomeSlider() {

    async function fetchMovies(){
        const res = await fetch("http://localhost:8080/api/movies/getMovies", {cache: 'no-store'});
        if (!res.ok) {
            throw new Error("Failed to fetch data");
        }
        const movies = await res.json();
        console.log(movies)
        return movies;
    }
    const movies = await fetchMovies();
    return ( 
        <>
        
            <section className="w-full h-[92vh] flex relative bg-black">
               <MovieSlider movies={movies} />
                
                
            </section>

        </>
     );
}

export default HomeSlider;