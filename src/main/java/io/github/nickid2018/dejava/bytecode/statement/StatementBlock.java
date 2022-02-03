/*
 * Copyright 2021 Nickid2018
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.nickid2018.dejava.bytecode.statement;

import io.github.nickid2018.dejava.util.Checkers;

import java.util.Arrays;

/**
 * A block of statements.
 */
public abstract class StatementBlock extends Statement implements Comparable<StatementBlock> {

    // Limit BLOCK DEPTH MUST BE LOWER THAN 256
    // AND BLOCK COUNT MUST BE LOWER THAN 32767
    // JVMS.: Variable table size must <256B
    // Value -> 1 ~ 32767, 0 means NOT REACH THE DEPTH
    private final short[] statementBlock = new short[256];

    /**
     * Create statement block with ZERO DEPTH.
     */
    public StatementBlock() {
    }

    /**
     * Create statement block with a parent block and an index.
     * @param blockParent parent of the block
     * @param index index of the block
     */
    public StatementBlock(StatementBlock blockParent, short index) {
        Checkers.errorIfFalse(index > 0, "A table has exceeded max index 32767.");
        int supDepth = blockParent.getDepth();
        Checkers.errorIfFalse(supDepth < 256, "Depth of a table has exceeded max depth 256.");
        System.arraycopy(blockParent.statementBlock, 0, statementBlock, 0, supDepth);
        statementBlock[supDepth] = index;
    }

    /**
     * Get the depth of statement block.
     * @return depth of statement block
     */
    public int getDepth() {
        int count = 0;
        for(;;count++)
            if (statementBlock[count] == 0)
                break;
        return count;
    }

    /**
     * Return whether this is the child of other block. A block is a child of its own.
     * @param block other block needs checking
     * @return true if the block is the child of other
     */
    public boolean isChildOf(StatementBlock block) {
        int head = block.getDepth();
        return Arrays.equals(statementBlock, 0, head, block.statementBlock, 0, head);
    }

    @Override
    public int compareTo(StatementBlock o) {
        for (int i = 0; i < 256; i++) {
            int thisValue = statementBlock[i];
            int thatValue = o.statementBlock[i];
            if (thisValue == 0 && thatValue == 0)
                return 0;
            if (thisValue != thatValue)
                return thisValue - thatValue;
        }
        return 0;
    }
}
