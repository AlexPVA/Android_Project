package csc207phase2.gamecentre;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.*;

public class SlidingTilesManagerUnitTest {

    @Test
    public void puzzleSolved_isCorrect() {
        SlidingTilesManager m = new SlidingTilesManager(5,5);
        assertFalse(m.puzzleSolved());
    }
    @Test
    public void isValidTapTrue_isCorrect() {
        SlidingTilesManager m = new SlidingTilesManager(3,3);
        boolean equal = false;
        if(m.getBoard().getTile(0,1).getId()==25 ||
                m.getBoard().getTile(1,0).getId()==25){
            equal = true;
        }
        assertEquals(m.isValidTap(0), equal);
    }
    @Test
    public void touchMove() {
        int position=0;
        int c=0;
        SlidingTilesManager m = new SlidingTilesManager(3,3);
        for(int row=0;row<3;row++){
            for(int col=0;col<3;col++) {
                if(m.getBoard().getTile(row,col).getId()==25)position=row*3+col;c=col;
            }
        }
        if(c!=2){
            m.touchMove(position+1);
        } else {
            m.touchMove(position-1);
        }
        assertTrue(m.isValidTap(position));
    }

    @Test
    public void undo_isCorrect() {
        int position=0;
        int c=0;
        int r=0;
        SlidingTilesManager m = new SlidingTilesManager(3,3);
        for(int row=0;row<3;row++){
            for(int col=0;col<3;col++) {
                if(m.getBoard().getTile(row,col).getId()==25) {
                    position = row * 3 + col;
                    r = row;
                    c = col;
                }
            }
        }
        if(c!=2){
            m.touchMove(position+1);
        } else {
            m.touchMove(position-1);
        }
        m.undo(m.stepSaver.undo());
        assertEquals(m.getBoard().getTile(r,c).getId(), 25);
    }

    @Test
    public void solvability_isCorrect(){
        SlidingTilesManager m = new SlidingTilesManager(3,3);
        assertTrue(m.boardSolvability(m.getBoard()));
    }


 }




