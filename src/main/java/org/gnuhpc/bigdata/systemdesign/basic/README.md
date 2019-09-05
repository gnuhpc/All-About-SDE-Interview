many modern databases are distributed in some way. There are two main motivations for this:
To scale beyond a single machine — storing and processing data in multiple nodes.
To increase availability — ensuring a database isn’t a single point of failure.
These two goals are closely related. In general, scaling a system by increasing the number of machines negatively affects availability, since the probability of encountering failures increases statistically. Thus, achieving high availability is almost a prerequisite to scalability.