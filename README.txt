This program is a skeleton framework to utilize the Auctionhouse JSON to develop a list of as many users from each server
as possible.

this is done by backtracing from the original AuctionHouse Json

AuctionHouse JSON
-> User Lists
-> Guild Users Belong To
-> Guild User List
-> Dump Characters in guild to database
-> API Call each user for specific data that is transient across all alts. (pet count, etc)
-> Store data back with the users Database


Then utilizing a basic web interface, we can QUERY the mySQL database for users of matching criterion, thus
tracking down known alt characters (for example the possibility of a user with the same pvp level and pet
count is pretty small, but this is in no way 100% accurate unless you utilize multiple fields- such as
achievement points with this)



the major assumption of this project is that at least one user from each guild has listed one item on the auction house

this will take serveral hundred if not thousand iterations to get a partial(never complete) guild list.