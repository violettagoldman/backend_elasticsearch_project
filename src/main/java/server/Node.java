package server;

import logic.*;

class Node {
	/*This class is the implementation of a node.
	A node stores all index. These index are splitted in shards.
	The master node will tell the node in which shard to store new documents,
	and in which shard to search for documents.
	This node is a HTTP server, it reveives requests only from the master node.
	It is really like a simple database, except there is NUMBER_OF_SHARDS smaller
	databases.
	*/

	final const int NUMBER_OF_SHARDS = 5;
	DataBase shards[NUMBER_OF_SHARDS];

	public Node() {
		for (int shard = 0; shard < NUMBER_OF_SHARDS; shard++) {
			shards[shard] = new DataBase("shard" + shard);
		}
	}

	/*Insert a document in the given shard and given table.*/
	public void insertDocument(int shard, String table, String document) {
		// shards[shard].getTables()[table].addLine(...);
	}

	/*Create a new table. We need to create this table in all shards.*/
	public void createTable(String table, String columns) {
		/*for (int shard = 0; shard < NUMBER_OF_SHARDS; shard++) {
			Table table = new Table(table, ...);
			shards[shard].putTable(table);
		}*/
	}

	/*Search in the given shard the rows where column matches value.*/
	public Document search(int shard, String table, String column, String value) {
		return shards[shard].selectFromWhere(table, column, value);
	}

	// Implement the HTTP servers function to call methods when the master node asks it...
}