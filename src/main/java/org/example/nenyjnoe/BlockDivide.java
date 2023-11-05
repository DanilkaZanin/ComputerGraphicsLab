package org.example.nenyjnoe;
public class BlockDivide {
    private static final int BLOCK_SIZE = 8;

    public static int[][][] divideIntoBlocks(int[][] yChannel) {
        int width = yChannel[0].length;
        int height = yChannel.length;

        int numBlocksX = width / BLOCK_SIZE;
        int numBlocksY = height / BLOCK_SIZE;

        int[][][] blocks = new int[numBlocksY][numBlocksX][BLOCK_SIZE * BLOCK_SIZE];

        for (int blockY = 0; blockY < numBlocksY; blockY++) {
            for (int blockX = 0; blockX < numBlocksX; blockX++) {
                int startY = blockY * BLOCK_SIZE;
                int startX = blockX * BLOCK_SIZE;

                for (int y = 0; y < BLOCK_SIZE; y++) {
                    for (int x = 0; x < BLOCK_SIZE; x++) {
                        blocks[blockY][blockX][y * BLOCK_SIZE + x] = yChannel[startY + y][startX + x];
                    }
                }
            }
        }

        return blocks;
    }

}