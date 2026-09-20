# MovieRep
SP1

We would like to be able to deliver the following functionality:

1. The database should contain all Danish movies (orginates from Denmark) from the TMDb API that has been released in the last 5 years. So just recent Danish movies. It should be around 1661 movies in total (give and take a few hundred).
   - Sovled! I added both DK and da, in the Api search, so i ended up with 1150 something movies
2. We would like to be able to see a list of all movies pulled from the database.
   - Solved!
3. Each movie has a list of actors and a director. We would like to be able to see a list of all actors and directors as well that have been part of those movies. You need to figure out how to fetch and store the actors and directors in the database. Also, what kind of relationship should there be between the entities?
   - Solved!
4. Each movie has a list of genres. We would like to be able to see a list of all genres as well. Also be able to list all movies within a particular genre. You need to figure out how to fetch and store the genres in the database. Also, what kind of relationship should there be between the entities?
   - Solved!
5. In case you want to add a new movie to the database, you should be able to do that as well. You should also be able to update and delete movies from the database. Not necessarily all fields, but at least the title and the release date.
   - Solved!
6. We would like to be able to search for a movie by title. The search should be case insensitive and should return all movies that contain the search string in the title.
   - Solved!
7. We would like to be able to get the total average rating of all movies in the database, the top-10 lowest and highest rated movies, and the top-10 most popular movies.
   - Solved!

Improvements:

- I think I could use some guiduance in writing actual good tests, since im really not sure these do the job as intended.
- Spending more time on error handling. easy to lose sight of when time is of the essence, which happend here
- Sadly, did not find a good way to implement threads