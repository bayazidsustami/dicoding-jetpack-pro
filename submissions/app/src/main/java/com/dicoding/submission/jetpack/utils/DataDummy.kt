package com.dicoding.submission.jetpack.utils

import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity

object DataDummy {

    private const val BASE_POSTER_PATH = "https://www.themoviedb.org/t/p/w1280"

    fun generateMovieData(): List<MoviesEntity> {
        val movies = mutableListOf<MoviesEntity>()
        movies.add(
            MoviesEntity(
                "460465",
                "$BASE_POSTER_PATH/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                "2021-04-07"
            )
        )
        movies.add(
            MoviesEntity(
                "804435",
                "$BASE_POSTER_PATH/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                "Vanquish",
                "2021-04-16"
            )
        )
        movies.add(
            MoviesEntity(
                "635302",
                "$BASE_POSTER_PATH/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "2020-10-16"
            )
        )
        movies.add(
            MoviesEntity(
                "615457",
                "$BASE_POSTER_PATH/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "Nobody",
                "2021-03-26"
            )
        )
        movies.add(
            MoviesEntity(
                "632357",
                "$BASE_POSTER_PATH/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                "The Unholy",
                "2021-03-31"
            )
        )
        movies.add(
            MoviesEntity(
                "615678",
                "$BASE_POSTER_PATH/duK11VQd4UPDa7UJrgrGx90xJOx.jpg",
                "Thunder Force",
                "2021-04-09"
            )
        )
        movies.add(
            MoviesEntity(
                "791373",
                "$BASE_POSTER_PATH/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "Zack Snyder's Justice League",
                "2021-03-18"
            )
        )
        movies.add(
            MoviesEntity(
                "634528",
                "$BASE_POSTER_PATH/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
                "The Marksman",
                "2021-01-15"
            )
        )
        movies.add(
            MoviesEntity(
                "726684",
                "$BASE_POSTER_PATH/msI5a9TPnepx47JUb2vl88hb80R.jpg",
                "Miraculous World: Shanghai – The Legend of Ladydragon",
                "2021-04-04"
            )
        )
        movies.add(
            MoviesEntity(
                "527774",
                "$BASE_POSTER_PATH/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "Raya and the Last Dragon",
                "2021-03-03"
            )
        )

        return movies
    }

    fun generateDetailMovie(): List<DetailMovieEntity> {
        val detailMovies = mutableListOf<DetailMovieEntity>()
        detailMovies.add(
            DetailMovieEntity(
                "460465",
                "https://www.mortalkombatmovie.net",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "804435",
                "https://www.lionsgate.com/movies/vanquish",
                "Vanquish",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "635302",
                "https://kimetsu.com/anime/movie/mugenressyahen/",
                "劇場版「鬼滅の刃」無限列車編",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "615457",
                "https://www.nobody.movie",
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "632357",
                "https://www.sonypictures.com/movies/theunholy",
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "615678",
                "https://www.netflix.com/ThunderForce",
                "Thunder Force",
                "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "791373",
                "https://www.hbomax.com/zacksnydersjusticeleague",
                "Zack Snyder's Justice League",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "634528",
                "https://www.themarksmanmovie.com",
                "The Marksman",
                "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "726684",
                "",
                "Miraculous World Shanghai, la légende de Ladydragon",
                "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "527774",
                "https://movies.disney.com/raya-and-the-last-dragon",
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "Released"
            )
        )
        return detailMovies
    }

    fun generateListTvShow(): List<TvShowsEntity> {
        val tvShows = mutableListOf<TvShowsEntity>()

        tvShows.add(
            TvShowsEntity(
                "88396",
                "$BASE_POSTER_PATH/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "The Falcon and the Winter Soldier"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "95557",
                "$BASE_POSTER_PATH/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "Invincible"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "71712",
                "$BASE_POSTER_PATH/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "The Good Doctor"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "60735",
                "$BASE_POSTER_PATH/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "The Flash"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "79008",
                "$BASE_POSTER_PATH/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
                "Luis Miguel: La Serie"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "1416",
                "$BASE_POSTER_PATH/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "Grey's Anatomy"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "120587",
                "$BASE_POSTER_PATH/Q1ZYG3kDS8iVIHOYOJ9NQmV0q7.jpg",
                "Haunted: Latin America"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "63174",
                "$BASE_POSTER_PATH/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "Lucifer"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "69050",
                "$BASE_POSTER_PATH/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "Riverdale"
            )
        )
        tvShows.add(
            TvShowsEntity(
                "65820",
                "$BASE_POSTER_PATH/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
                "Van Helsing"
            )
        )
        return tvShows
    }
}