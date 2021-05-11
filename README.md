# backend_elasticsearch_project

## How is the data distributed

We use a simplified version of the ElasticSearch logic to distribute data accross different nodes.

Every index is divided in a constant number (default value of 5) of shards.

A shard is a slice of the index, and all shards return the whole index.

Example: we have an index "movies". We have 20 movies indexed. The 20 movies will be spread accross the 5 shards.
The shard 1 could contain movies 1, 18 and 7, the shard 3 could contain the movie 11... If we gather
all the shards together we retrieve our 20 movies.

To decide on which shard to store a new document, we use the hash of the document modulo the number
of shards. This way new documents are randomly sent to shards. Like ElasticSearch, we use the
Murmur3 hash function to hash documents, as this function is proven to have a great distribution (so
we can keep balanced shards).

-> faire juste le hash, et expliquer les replicas -> capable d'expliquer que ça marche -> tester avec les noeds qu'on retire à fur et à mesure

Every node stores all the index and their shards. It means the data is the same
on every nodes. If one node fails, the other still have all the data.

When we search data, we will choose the shards on random nodes and aggregate the result.
This way, the search is done by multiple nodes at the same time.

```
-- Node 1 ---
 > Index "Movies"
   > Shard 1
   > Shard 2
   > Shard 3
 > Index "Authors"
   > Shard 1
   > Shard 2
   > Shard 3
-- Node 2 ---
 > Index "Movies"
   > Shard 1
   > Shard 2
   > Shard 3
 > Index "Authors"
   > Shard 1
   > Shard 2
   > Shard 3
...

Example of inserting a document:
We have 2 nodes, 3 shards per index.
> We add the document "The Birds,Hitchcock,1963" to the index "Movies"
1. The master node calculates the hash of the document, it is 2364976411.
2. It calculates the hash modulo the number of shards, which gives 1 (Shard 2).
3. The master node tells Node 1 and Node 2 to add the document to Shard 2 of the index Movies.

Example of searching a document.
We still have 2 nodes, 3 shards per index.
> We search for all document with year 1963 in the index "Movies"
1. The master node chooses random nodes for every shard. It decides to ask Node 1 for Shard 1 and 3, an Node 2 for Shard 2.
3. Node 1 returns the rows in Shard 1 and 3 with year=1963.
3. Node 2 returns the rows in Shard 2 with year=1963.
4. The master node takes all the returned response and returns it to the user.
```

To implement:
- implement HTTP server in Node (copy-paste the master server code)
- implement createTable function in the master node (Server)