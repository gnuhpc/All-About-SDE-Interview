We design the system architecture of Tinder. Tinder is an app used for dating and socializing. Designing these apps starts with clarifying the system requirements. In an interview, Tinder has multiple requirements.

When designing the system, we will be drawing logical components and defining their interactions with other services based on the microservice architecture. We assume that any internal details can be handled with our proir knowledge of system design concepts. Check the reference section for a set of links.

The Tinder system has four requirements: storing profiles, recommendations, noting matches and chatting with matches. Storing profiles is trivial except for the image storage, where we argue on BLOB vs File storage. The distributed file architecture seems best when storing images.

Direct Messaging or chatting with matches can be done using the XMPP protocol, which uses web sockets to have peer to peer communications between client and server. Each connection is built over TCP, ensuring that the connection is maintained. The session microservice can send messages to the receiver based on connection to user mappings.

Our system is decoupled as much as possible. We try to maintain accept and reject information on the client. On swiping left or right, the client can note the action and avoid showing the same user again, perhaps using bloom filters.

The server has a validation engine called the matcher microservice, which notes matches and allows or disallows chat between two users.

The final requirement of recommendations needs city wise partitioning on the user data. This is acheived using NoSQL databases like Cassandra or Amazon Dynamo. The other option is to use relational databases with horizontal partitioning. The concept is now refered to as sharding.

If you have any doubts, suggestions or feedback, I love to hear them below. This system is by no means complete, but it is enough for an hour long interview.
