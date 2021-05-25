package com.dicoding.submission.jetpack.utils

import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.movies.*
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.LastEpisodeToAir
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.NetworksItem
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.SeasonsItem
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.BaseListResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemMovie
import com.dicoding.submission.jetpack.data.dataSource.remote.response.list.ResultsItemTv

object DummyResponse {

    fun getDummyListMovie(): BaseListResponse<ResultsItemMovie>{
        val dummyData = BaseListResponse<ResultsItemMovie>()
        dummyData.page = 1
        dummyData.totalPages = 500
        dummyData.totalResults = 10000
        dummyData.results = listOf(
            ResultsItemMovie(
                overview =  "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                originalLanguage = "en",
                id = 460465,
                adult = false,
                backdropPath = "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                genreIds = listOf(28, 14, 12, 878),
                originalTitle = "Mortal Kombat",
                popularity = 5817.001,
                posterPath = "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                releaseDate = "2021-04-07",
                title = "Mortal Kombat",
                video = false,
                voteAverage = 7.7,
                voteCount = 2266
            ),
            ResultsItemMovie(
                overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                originalLanguage = "en",
                id = 399566,
                adult = false,
                backdropPath = "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                genreIds = listOf(878, 28, 18),
                originalTitle = "Godzilla vs. Kong",
                popularity = 3608.866,
                posterPath = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                releaseDate = "2021-03-24",
                title = "Godzilla vs. Kong",
                video = false,
                voteAverage = 8.1,
                voteCount = 5373
            ),
            ResultsItemMovie(
                adult = false,
                backdropPath = "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
                genreIds = listOf(28, 12, 53, 10752),
                id = 567189,
                originalLanguage = "en",
                originalTitle = "Tom Clancy's Without Remorse",
                overview = "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                popularity = 4266.181,
                posterPath = "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                releaseDate = "2021-04-29",
                title = "Tom Clancy's Without Remorse",
                video = false,
                voteAverage = 7.3,
                voteCount = 696
            )
        )
        return dummyData
    }

    fun getDummyDetailMovie(): DetailMovieResponse{
        return DetailMovieResponse(
            adult = false,
            backdropPath = "/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
            belongsToCollection = null,
            budget = 0,
            genres = listOf(
                TvGenresItem(id = 15, name = "Animation"),
                TvGenresItem(id = 12, name = "Adventure"),
                TvGenresItem(id = 14, name = "Fantasy"),
                TvGenresItem(id = 10751, name = "Family"),
                TvGenresItem(id = 28, name = "Action")
            ),
            homepage = "https://movies.disney.com/raya-and-the-last-dragon",
            id = 527774,
            imdbId = "tt5109280",
            originalLanguage = "en",
            originalTitle = "Raya and the Last Dragon",
            overview = "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
            popularity = 1392.812,
            posterPath = "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
            productionCompanies = listOf(
                ProductionCompaniesItem(id = 2, logoPath = "/wdrCwmRnLFJhEoH8GSfymY85KHT.png", name = "Walt Disney Pictures", originCountry = "us"),
                ProductionCompaniesItem(id = 6125, logoPath = "/tVPmo07IHhBs4HuilrcV0yujsZ9.png", name = "Walt Disney Animation Studios", originCountry = "us")
            ),
            productionCountries = listOf(
                ProductionCountriesItem(iso31661 = "us", name = "United States of America")
            ),
            releaseDate = "2021-03-03",
            revenue = 56482606,
            runtime = 107,
            spokenLanguages = listOf(
                SpokenLanguagesItem(englishName = "English", iso6391 = "en", name = "English")
            ),
            status = "Released",
            tagline = "A quest to save her world.",
            title = "Raya and the Last Dragon",
            video = false,
            voteAverage = 8.2,
            voteCount = 2724
        )
    }

    fun getDummyListTvShow(): BaseListResponse<ResultsItemTv>{
        val dataDummy = BaseListResponse<ResultsItemTv>()
        dataDummy.totalResults = 10000
        dataDummy.totalPages = 500
        dataDummy.page = 1
        dataDummy.results = listOf(
            ResultsItemTv(
                backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                firstAirDate = "2021-03-19",
                genreIds = listOf( 10765, 10759, 18, 10768),
                id = 88396,
                name = "The Falcon and the Winter Soldier",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Falcon and the Winter Soldier",
                overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                popularity = 1708.193,
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                voteAverage = 7.9,
                voteCount = 5378
            ),
            ResultsItemTv(
                backdropPath = "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                firstAirDate = "2021-03-26",
                genreIds = listOf(16, 10759, 18, 10765),
                id = 95557,
                name = "Invincible",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Invincible",
                overview = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                popularity =  1570.159,
                posterPath = "/4UEfVAuI4Yn09nzJ16NFR1pv3ac.jpg",
                voteAverage = 8.9,
                voteCount = 1442
            ),
            ResultsItemTv(
                backdropPath = "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                firstAirDate = "2014-10-07",
                genreIds = listOf(18, 10765),
                id = 60735,
                name = "The Flash",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Flash",
                overview =  "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                popularity =  1258.631,
                posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                voteAverage = 7.7,
                voteCount = 7552
            )
        )
        return dataDummy
    }

    fun getDummyDetailTvShow(): DetailTvShowResponse{
        return DetailTvShowResponse(
            backdropPath = "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
            createdBy = emptyList(),
            episodeRunTime = listOf(45),
            firstAirDate = "2021-03-26",
            genres = listOf(
                TvGenresItem(id = 16, name = "animation"),
                TvGenresItem(id = 10759, name = "Action & Adventure"),
                TvGenresItem(id = 18, name = "Drama"),
                TvGenresItem(id = 10765, name = "Sci-Fi & Fantasy")
            ),
            homepage = "https://www.amazon.com/dp/B08WJP55PR",
            id = 95557,
            inProduction = true,
            languages = listOf("en"),
            lastAirDate = "2021-04-30",
            lastEpisodeToAir = LastEpisodeToAir(
                airDate = "2021-04-30",
                episodeNumber = 8,
                id = 2832752,
                name = "WHERE I REALLY COME FROM",
                overview = "Mark must prove he's become the hero he's always wanted to be by stopping an unstoppable force.",
                productionCode = "",
                seasonNumber = 1,
                stillPath = "/ijDV8Z23iR5B2ftx0WggiXbfqGi.jpg",
                voteAverage = 9.5,
                voteCount = 2
            ),
            name = "Invincible",
            nextEpisodeToAir = null,
            networks = listOf(
                NetworksItem(
                    name = "Amazon",
                    id = 1024,
                    logoPath = "/ifhbNuuVnlwYy5oXA5VIb2YR8AZ.png",
                    originCountry = ""
                )
            ),
            numberOfEpisodes = 8,
            numberOfSeasons = 1,
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "Invincible",
            overview = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            popularity = 1570.159,
            posterPath =  "/4UEfVAuI4Yn09nzJ16NFR1pv3ac.jpg",
            productionCompanies = listOf(
                com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.ProductionCompaniesItem(
                    id = 20580,
                    logoPath =  "/tkFE81jJIqiFYPP8Tho57MXRQEx.png",
                    name = "Amazon Studios",
                    originCountry = "US"
                ),
                com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.ProductionCompaniesItem(
                    id = 151645,
                    logoPath = null,
                    name = "Skybound North",
                    originCountry = "CA"
                ),
                com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.ProductionCompaniesItem(
                    id = 50032,
                    logoPath = null,
                    name = "Skybound Entertainment",
                    originCountry = "US"
                )
            ),
            productionCountries = listOf(
                com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.ProductionCountriesItem(
                    iso31661 = "CA",
                    name = "Canada"
                ),
                com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.ProductionCountriesItem(
                    iso31661 = "US",
                    name = "United States of America"
                )
            ),
            seasons = listOf(
                SeasonsItem(
                    airDate = "2021-03-26",
                    episodeCount = 8,
                    id = 136020,
                    name = "season 1",
                    overview = "",
                    posterPath = "/zCFpCpg4XL1bH9yNTn6WY4hx7wu.jpg",
                    seasonNumber = 1
                )
            ),
            spokenLanguages = listOf(
                com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.SpokenLanguagesItem(
                    englishName = "english",
                    iso6391 = "en",
                    name = "english"
                )
            ),
            status = "Returning Series",
            tagline = "",
            type = "Scripted",
            voteAverage = 8.9,
            voteCount = 1462
        )
    }

}