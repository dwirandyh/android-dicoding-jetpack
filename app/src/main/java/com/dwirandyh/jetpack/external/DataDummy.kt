package com.dwirandyh.jetpack.external

import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.domain.model.TvShowModel
import kotlinx.coroutines.*
import kotlin.collections.ArrayList

object DataDummy {

    suspend fun generateMovieList(): ArrayList<MovieModel> {
        delay(2000L)

        val movieList = ArrayList<MovieModel>()

        movieList.add(
            MovieModel(
                "131634",
                "Parasite (2019)",
                4.2,
                "2019-01-11".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                "2h 40m",
                "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident."
            )
        )

        movieList.add(
            MovieModel(
                "131632",
                "Shrek 2 (2014)",
                4.0,
                "2004-06-23".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/oljiDFPyMY437BRRV69AzVDSiKy.jpg",
                "2h 40m",
                "Shrek, Fiona and Donkey set off to Far, Far Away to meet Fiona's mother and father. But not everyone is happy. Shrek and the King find it hard to get along, and there's tension in the marriage. The fairy godmother discovers that Shrek has married Fiona instead of her Son Prince Charming and sets about destroying their marriage."
            )
        )

        movieList.add(
            MovieModel(
                "131631",
                "The Hunger Games: Mockingjay - Part 1 (2014)",
                4.2,
                "2019-01-11".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                "2h 40m",
                "With the nation of Panem in a full scale war, Katniss confronts President Snow in the final showdown. Teamed with a group of her closest friends – including Gale, Finnick, and Peeta – Katniss goes off on a mission with the unit from District 13 as they risk their lives to stage an assassination attempt on President Snow who has become increasingly obsessed with destroying her. The mortal traps, enemies, and moral choices that await Katniss will challenge her more than any arena she faced in The Hunger Games"
            )
        )

        movieList.add(
            MovieModel(
                "131635",
                "Better Days (2019)",
                4.5,
                "2019-08-11".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/ev8PDIuKm2j3m3jWxw497zCLjzu.jpg",
                "2h 40m",
                "A bullied teenage girl forms an unlikely friendship with a mysterious young man who protects her from her assailants, all while she copes with the pressures of her final examinations."
            )
        )

        movieList.add(
            MovieModel(
                "291805",
                "Now You See Me 2 (2016)",
                4.3,
                "2016-10-06".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/A81kDB6a1K86YLlcOtZB27jriJh.jpg",
                "2h 20m",
                "One year after outwitting the FBI and winning the public’s adulation with their mind-bending spectacles, the Four Horsemen resurface only to find themselves face to face with a new enemy who enlists them to pull off their most dangerous heist yet."
            )
        )

        movieList.add(
            MovieModel(
                "447665",
                "Kickboxer: Retaliation (2018)",
                4.3,
                "2018-01-26".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/oMWP4cAoy8WBauuKZAVTIfuY3Fw.jpg",
                "2h 30m",
                "One year after the events of \"Kickboxer: Vengeance\", Kurt Sloan has vowed never to return to Thailand. However, while gearing up for a MMA title shot, he finds himself sedated and forced back into Thailand, this time in prison. He is there because the ones responsible want him to face a 6'8\" 400 lbs. beast named Mongkut and in return for the fight, Kurt will get two million dollars and his freedom back. Kurt at first refuses, in which a bounty is placed on his head as a way to force him to face Mongkut. Kurt soon learns he will have no other choice and will undergo his most rigorous training yet under some unexpected mentors in order to face Mongkut in hopes to regain his freedom."
            )
        )

        movieList.add(
            MovieModel(
                "518068",
                "Along with the Gods: The Last 49 Days",
                4.3,
                "2018-01-08".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/9BnqBHFGDv3WlCPB2qQwzAWdy7y.jpg",
                "2h 30m",
                "In the afterlife, one guardian helps a man through his trials, while his two colleagues help a former guardian on earth."
            )
        )

        movieList.add(
            MovieModel(
                "376290",
                "Miss Sloane",
                4.8,
                "2016-12-22".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/ptfvQlqe2xJiMSewSj52qAVq5z0.jpg",
                "2h 30m",
                "An ambitious lobbyist faces off against the powerful gun lobby in an attempt to pass gun control legislation."
            )
        )

        movieList.add(
            MovieModel(
                "396555",
                "Train to Busan",
                4.7,
                "2016-07-20".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/3H1WFCuxyNRP35oiL2qqwhAXxc0.jpg",
                "2h 30m",
                "Martial law is declared when a mysterious viral outbreak pushes Korea into a state of emergency. Those on an express train to Busan, a city that has successfully fended off the viral outbreak, must fight for their own survival"
            )
        )

        movieList.add(
            MovieModel(
                "570508",
                "Innocent Witness",
                4.1,
                "2019-02-13".convertToDate("yyyy-MM-dd"),
                "https://image.tmdb.org/t/p/w500/tmIWkYE9h0Ilbbe7xGTHjawa5QQ.jpg",
                "2h 30m",
                "An old man suffering from depression is found dead, and his housekeeper, Mi-ran, is charged with the murder. Mi-ran’s defense attorney, Sun-ho, is surprised to learn the only witness to the crime is Ji-woo, a teenage girl with Asperger’s. Will Ji-woo be able to take the stand and provide a valid statement?"
            )
        )

        return movieList
    }

    suspend fun generateTvList(): ArrayList<TvShowModel> {
        delay(2000L)

        val tvList = ArrayList<TvShowModel>()

        tvList.add(
            TvShowModel(
                "88396",
                "The Falcon and the Winter Soldier",
                4.7,
                "2021-19-03".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "40m",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )
        )

        tvList.add(
            TvShowModel(
                "60735",
                "The Flash",
                4.7,
                "2014-10-07".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                "40m",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
            )
        )

        tvList.add(
            TvShowModel(
                "60725",
                "Riverdale",
                4.3,
                "2017-01-26".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                "45m",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade."
            )
        )

        tvList.add(
            TvShowModel(
                "1416",
                "Grey's Anatomy",
                4.3,
                "2005-03-27".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "45m",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives"
            )
        )

        tvList.add(
            TvShowModel(
                "71712",
                "The Good Doctor",
                4.5,
                "2017-09-25".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "40m",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
            )
        )

        tvList.add(
            TvShowModel(
                "95057",
                "Superman & Lois",
                4.1,
                "2021-02-23".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gmbsR4SvYhhj4SvLAlTKxIkFxp9.jpg",
                "40m",
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society."
            )
        )

        tvList.add(
            TvShowModel(
                "1452",
                "The Walking Dead",
                4.2,
                "2010-10-31".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                "40m",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way."
            )
        )

        tvList.add(
            TvShowModel(
                "85231",
                "WandaVision",
                4.3,
                "2021-01-15".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/57vVjteucIF3bGnZj6PmaoJRScw.jpg",
                "40m",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems."
            )
        )

        tvList.add(
            TvShowModel(
                "63171",
                "Lucifer",
                4.3,
                "2016-01-25".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                "40m",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit."
            )
        )

        tvList.add(
            TvShowModel(
                "120118",
                "Who Killed Sara?",
                4.3,
                "2021-03-24".convertToDate("yyyy-MM-dd"),
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                "40m",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
            )
        )

        return tvList
    }
}