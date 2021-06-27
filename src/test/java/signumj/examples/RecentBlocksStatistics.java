package signumj.examples;

import signumj.Constants;
import signumj.entity.SignumAddress;
import signumj.entity.SignumValue;
import signumj.entity.response.Account;
import signumj.entity.response.Block;
import signumj.service.NodeService;

/**
 * Prints the statistics for recent blocks.
 *
 */
public class RecentBlocksStatistics {

	public static void main(String[] args) {
		
        NodeService node = NodeService.getInstance(Constants.HTTP_NODE_EUROPE2);
		
		int NBLOCKS = 120;
		
		int smallestDeadline = Integer.MAX_VALUE;
		int largestDeadline = 0;
		
		float avDeadline = 0;
		float avEC = 0;
		
		Block[] blocks = node.getBlocks(0, NBLOCKS).blockingGet();

		System.out.println("height\tdeadline\ttarget\tEC\tAverage Commitment (SIGNA/Tb)");
		for(int i = 1; i<blocks.length; i++) {
			Block next = blocks[i];
			Block b = blocks[i-1];
			
			SignumAddress generator = b.getGenerator();
			Account generatorAccount = node.getAccount(generator, null, true, null).blockingGet();

			int deadline = b.getTimestamp().getTimestamp() - next.getTimestamp().getTimestamp();

			smallestDeadline = Math.min(smallestDeadline, deadline);
			largestDeadline = Math.max(largestDeadline, deadline);

			avDeadline += deadline;
			float EC = 18325193796f/b.getBaseTarget()/1.83f;
			avEC += EC;
			System.out.println(b.getHeight() + "\t" + deadline + "\t" + b.getBaseTarget() + "\t" + EC + "\t" + SignumValue.fromNQT(b.getAverageCommitmentNQT()).toUnformattedString() +
					"\t" + generatorAccount.getCommitment().doubleValue());
		}
        avDeadline /= blocks.length;
        avEC /= blocks.length;
		
		double stdDev = 0;
		for(int i = 1; i<blocks.length; i++) {
			Block next = blocks[i];
			Block b = blocks[i-1];
			
			int deadline = b.getTimestamp().getTimestamp() - next.getTimestamp().getTimestamp();
			
			double dev = deadline-avDeadline;
			stdDev += dev*dev;
		}
		stdDev = Math.sqrt(stdDev/blocks.length);
		
        System.out.println("average deadline: " + avDeadline);
        System.out.println("average EC: " + avEC);
		System.out.println("smallest deadline: " + smallestDeadline);
		System.out.println("largest deadline: " + largestDeadline);
		System.out.println("deadline std dev: " + stdDev);

		System.exit(0);
	}
}
