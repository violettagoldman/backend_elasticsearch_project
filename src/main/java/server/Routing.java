package server;

import java.util.Map;
import org.apache.commons.codec.digest.MurmurHash3;
import java.util.Random;

public class Routing {

	/* Get the shard id (from 0 to numberOfShards - 1) to store the given
	document in.
	Params:
	- body: string, string of the document to index
	- numberOfShards: int, the number of total shards for one index
	*/
	public static int getShard(String document, int numberOfShards) {
		int hash = MurmurHash3.hash32(new String(document).getBytes());
		hash = hash < 0 ? -hash : hash;
		return hash % numberOfShards;
	}

	/* Get the routes for every shards.
	Return a map of <int, int>. The first int is the shard number. The second int is the
	corresponding node.
	It means that for every pair, we should query the node pair[1] for the shard pair[0].
	Params:
	- numberOfNodes: int, the total number of nodes running
	- numberOfShards: int, the number of total shards for one index
	*/
	public static Map<int, int> getRoutes(int numberOfNodes, int numberOfShards) {
		Random random = new Random();
		Map<int, int> shardNodes = new Map<int, int>();
		for (int shard = 0; shard < numberOfShards; shard++) {
			shardNodes[shard] = random.nextInt(numberOfNodes);
		}
		return shardNodes;
	}

}