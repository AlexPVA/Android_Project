package csc207phase2.gamecentre;

import java.util.Stack;

/**
 * The step saver for undo functionality.
 */
class StepSaver extends Stack<Integer[]> {
    
    /**
     * Initialize the stack for steps.
     */
    StepSaver(){
        super();
    }

    /**
     * Record and save the step in StepSaver.
     *
     * @param a,b,c,d the positions of two tiles being swapped
     *
     */
    void recordMove(Integer a, Integer b, Integer c, Integer d){
        Integer[] pos = new Integer[4];
        Integer[] f;
        Integer[] s;
        pos[0] = a;
        pos[1] = b;
        pos[2] = c;
        pos[3] = d;
        if (this.size() >= 3){
            f = this.pop();
            s = this.pop();
            this.clear();
            this.push(s);
            this.push(f);
            this.push(pos);
        }
        else{this.push(pos);}
    }

    /**
     * Return the previous step.
     *
     * @return pos the positions of two tiles previously swapped
     *
     */
    Integer[] undo(){
        Integer[] pos = new Integer[4];
        if (!this.empty()){pos = this.pop();
            }
        return pos;
    }

}