package csc207phase2.gamecentre;

/**
 * Manage a board
 */
class MinesweeperManager extends GameManager {

    /**
     * The board being managed.
     */
    private MinesweeperBoard board;

    /**
     * Account name of the current user.
     */
    private String accountName;

    /**
     * The number of moves the boardmanager has processed.
     */
    private int numMoves;

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    MinesweeperManager(MinesweeperBoard board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    MinesweeperBoard getBoard() {
        return board;
    }

    /**
     * Sorter for the ScoreBoard
     */
    private static ScoreSorter<Score> scoreSorter = new MinimumSorter<Score>();

    /**
     * Stores the scores for sliding tiles game.
     */
    private static ScoreBoard<Score> scores = new ScoreBoard<Score>(scoreSorter);

    /**
     * The game this is a part of.
     */
    transient private GameComponent game;

    /**
     * Whether the current game has been lost (waits for next tap to reset)
     */
    private boolean gameLost;

    /**
     * Manage a new shuffled board.
     */
    MinesweeperManager(int row, int col) {
        this.board = new MinesweeperBoard(row, col);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        if(gameLost){
            return false;
        }

        int untapped = 0;

        //find the number of untapped tiles
        for (MinesweeperTile tile: board) {
            if (!tile.isTapped()) {
                untapped++;
            }
        }
        if (board.numBombs() == untapped) {
            Score newScore = new Score(this.getAccountName(),
                    "Sliding Tiles: " + this.getBoard().getNumRows());
            newScore.setScorePoint(this.getNumMoves());
            this.scores.addScore(newScore);
            //if number of bombs == number of untapped spaces the game ends
            return true;
        }else{
            return false;
        }


    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return
     */
    boolean isValidTap(int position) {
        int row = position / board.getNumCols();
        int col = position % board.getNumCols();

        return !(board.getTile(row, col).isTapped()) || gameLost;
    }

    /**
     * Process a touch at position in the board.
     *
     * @param position the position
     */
    void touchMove(int position) {
        int row = position / board.getNumCols();
        int col = position % board.getNumRows();

        MinesweeperTile tile = board.getTile(row, col);
        if(!tile.isTapped()){
            numMoves++;
            board.tapTile(row, col);
        }

        if(gameLost){
            setGameLost(false);
        }else if(tile.getId() == MinesweeperTile.BOMB_ID){
            tile.setId(MinesweeperTile.EXPLODED_BOMB_ID);
            setGameLost(true);
        }

    }

    void setGameLost(boolean lost){
        this.gameLost = lost;

        if(lost){
            for(int i = 0; i < board.getNumRows(); i++){
                for(int j = 0; j < board.getNumCols(); j++){
                    board.tapTile(i, j);
                }
            }
        }else{
            board.generateBoard(board.getNumRows(), board.getNumCols());
        }
    }

    /**
     * Sets the game this is a part of.
     *
     * @param game the game object this is a part of
     */
    public void setGame(GameComponent game){
        this.game = game;
    }

    /**
     * Return the name of the current user account.
     * @return the name of the current user account.
     */
    public String getAccountName() {return this.accountName; }

    /**
     * Return the number of moves this boardmanager has processed.
     * @return the number of moves this boardmanager has processed.
     */
    public int getNumMoves() {
        return numMoves;
    }

    @Override
    public void autoSave(String fileName) {
        game.saveToFile(fileName);
    }
}