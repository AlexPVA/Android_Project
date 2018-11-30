package csc207phase2.gamecentre;

import java.util.Stack;

/**
 * The step saver for undo functionality.
 */
class StepSaver extends Stack<Integer[]> {

    /**
     * The default undoable steps.
     */
    private int undoCount = 3;

    /**
     * Initialize the stack for steps.
     */
    StepSaver() {
        super();
    }

    /**
     * Record and save the step in StepSaver.
     *
     * @param a,b,c,d the positions of two tiles being swapped
     */
    void recordMove(Integer a, Integer b, Integer c, Integer d) {
        Integer[] pos = new Integer[4];
        Integer[][] steps = new Integer[undoCount][4];
        pos[0] = a;
        pos[1] = b;
        pos[2] = c;
        pos[3] = d;
        if (this.size() >= undoCount) {
            for (int i = 1; i < undoCount; i++) {
                steps[i] = this.pop();

            }
            steps[0] = pos;
            this.clear();
            for (int j = undoCount - 1; j > -1; j--) {
                this.push(steps[j]);
            }
        } else {
            this.push(pos);
        }
    }

    /**
     * Return the previous step.
     *
     * @return pos the positions of two tiles previously swapped
     */
    public Integer[] undo() {
        Integer[] pos = new Integer[4];
        if (!this.empty()) {
            pos = this.pop();
        }
        return pos;
    }

}