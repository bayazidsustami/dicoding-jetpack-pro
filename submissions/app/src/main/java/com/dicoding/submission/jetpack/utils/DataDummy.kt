package com.dicoding.submission.jetpack.utils

import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity

object DataDummy {

    const val BASE_POSTER_PATH = "https://www.themoviedb.org/t/p/w1280"

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
                "Miraculous World: Shanghai ??? The Legend of Ladydragon",
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
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters???or she may never see her child again.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "635302",
                "https://kimetsu.com/anime/movie/mugenressyahen/",
                "??????????????????????????????????????????",
                "Tanjir?? Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Ky??jur?? Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "615457",
                "https://www.nobody.movie",
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor ??? a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
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
                "Jim Hanson???s quiet life is suddenly disturbed by two people crossing the US/Mexico border ??? a woman and her young son ??? desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy???s reluctant defender. He embraces his role as Miguel???s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "726684",
                "",
                "Miraculous World Shanghai, la l??gende de Ladydragon",
                "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
                "Released"
            )
        )
        detailMovies.add(
            DetailMovieEntity(
                "527774",
                "https://movies.disney.com/raya-and-the-last-dragon",
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it???s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
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
                "The Falcon and the Winter Soldier",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "95557",
                "$BASE_POSTER_PATH/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "Invincible",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "71712",
                "$BASE_POSTER_PATH/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "The Good Doctor",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "60735",
                "$BASE_POSTER_PATH/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "The Flash",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "79008",
                "$BASE_POSTER_PATH/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
                "Luis Miguel: La Serie",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "1416",
                "$BASE_POSTER_PATH/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "Grey's Anatomy",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "120587",
                "$BASE_POSTER_PATH/Q1ZYG3kDS8iVIHOYOJ9NQmV0q7.jpg",
                "Haunted: Latin America",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "63174",
                "$BASE_POSTER_PATH/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "Lucifer",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "69050",
                "$BASE_POSTER_PATH/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "Riverdale",
                false
            )
        )
        tvShows.add(
            TvShowsEntity(
                "65820",
                "$BASE_POSTER_PATH/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
                "Van Helsing",
                false
            )
        )
        return tvShows
    }

    fun generateDetailTvShow(): List<DetailTvShowsEntity>{
        val detailTvShow = mutableListOf<DetailTvShowsEntity>()

        detailTvShow.add(DetailTvShowsEntity(
            "88396",
            "$BASE_POSTER_PATH/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "https://www.disneyplus.com/series/the-falcon-and-the-winter-soldier/4gglDBMx8icA",
            "The Falcon and the Winter Soldier",
            "Following the events of ???Avengers: Endgame???, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "Ended",
            "6"
        ))
        detailTvShow.add(
            DetailTvShowsEntity(
            "95557",
            "$BASE_POSTER_PATH/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            "https://www.amazon.com/dp/B08WJP55PR",
            "Invincible",
            "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father???s tutelage.",
                "Returning Series",
                "8"
        )
        )
        detailTvShow.add(
            DetailTvShowsEntity(
            "71712",
                "$BASE_POSTER_PATH/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "http://abc.go.com/shows/the-good-doctor",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "Returning Series",
                "76"
        )
        )
        detailTvShow.add(DetailTvShowsEntity(
            "60735",
            "$BASE_POSTER_PATH/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            "http://www.cwtv.com/shows/the-flash/",
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "Returning Series",
            "146"
        ))

        detailTvShow.add(
            DetailTvShowsEntity(
            "79008",
            "$BASE_POSTER_PATH/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
            "https://www.netflix.com/title/80191236",
            "Luis Miguel: La Serie",
            "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
            "Returning Series",
            "8"
        )
        )
        detailTvShow.add(
            DetailTvShowsEntity(
            "1416",
                "$BASE_POSTER_PATH/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "http://abc.go.com/shows/greys-anatomy",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle???s Grey Sloan Memorial Hospital.",
                "Returning Series",
                "16"
        )
        )
        detailTvShow.add(
            DetailTvShowsEntity(
            "120587",
            "$BASE_POSTER_PATH/Q1ZYG3kDS8iVIHOYOJ9NQmV0q7.jpg",
            "https://www.netflix.com/title/81215555",
            "Haunted: Latinoam??rica",
            "Real people's terrifying tales of the chilling, unexplained and paranormal come to life with dramatic reenactments in this reality series.",
            "Returning Series",
            "5"
        )
        )
        detailTvShow.add(DetailTvShowsEntity(
            "63174",
            "$BASE_POSTER_PATH/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "https://www.netflix.com/title/80057918",
            "Lucifer",
            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals.??But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            "Returning Series",
            "93"
        ))
        detailTvShow.add(
            DetailTvShowsEntity(
            "69050",
            "$BASE_POSTER_PATH/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            "http://www.cwtv.com/shows/riverdale/",
            "Riverdale",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale???s wholesome facade.",
            "Returning Series",
            "11"
        )
        )
        detailTvShow.add(
            DetailTvShowsEntity(
            "65820",
            "$BASE_POSTER_PATH/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
            "http://www.syfy.com/vanhelsing",
            "Van Helsing",
            "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity???s last hope to lead an offensive to take back what has been lost.",
            "Returning Series",
            "4"
        )
        )

        return detailTvShow
    }
}