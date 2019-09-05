# Programming in depth – Exercise 4 – Web Services 

You have about 5 kids at home (for those of you who don't, you can just imagine it). And as with all kids they love 
[Pokémon](https://en.wikipedia.org/wiki/Pok%C3%A9mon) and
since they know you are a programming expert (if you're not, just imagine this as well) they have asked you to create an 
application where they can look up different pokemons.

In this case, your program is going to need access to data about pokemons and you could create that data yourself manually, for
example in a text file or in an SQL database, but that would take quite some time. It would be much more convenient to use an
existing one that someone else is maintaining. A way to solve this is by using a web service which provides the necessary data. 

### Web services
A web service is an application that can be communicated with through the web via a standard protocol (HTTP/HTTPS etc.) and are
usually designed to communicate with other programs rather than humans. What does this mean? Take an example from
[Meta Weather](https://www.metaweather.com/), a website for checking the weather. If we want to check the weather in
London on the 4th of April 2013 we can do it the "normal" way:

https://www.metaweather.com/44418/2013/4/27/

or we could access the same data via their webservice:

https://www.metaweather.com/api/location/44418/2013/4/27/

As you can see, we can conlude the following:
- The first one is more human-readable.
- The second one is raw JSON which will be easier to handle in a program.
- The weather in London has always been and always will be utterly disappointing.

## Task
You are to create a java application which will act as a small pokemon encyclopedia (aka. a 
[pokedex](https://bulbapedia.bulbagarden.net/wiki/Pok%C3%A9dex)). You should be able to do the following:

- Enter a name of a pokémon and get the id, name, height and weight.
- Enter a name of a location and get the area, region and name
- Add more information to the pokémon lookup so that it also shows the pokémon type and lists the type weaknesses
and strengths respectively.
