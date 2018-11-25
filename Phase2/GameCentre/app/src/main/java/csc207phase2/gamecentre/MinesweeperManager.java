package csc207phase2.gamecentre;

/**
 * Manage a minesweeper board
 */
class MinesweeperManager extends BoardManager {

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
     * Stores the scores for minesweeper game.
     */
    private static ScoreBoard scores = new ScoreBoard(scoreSorter, 6);

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
     *
     * @param row the number of rows
     * @param col the number of cols
     */
    MinesweeperManager(int row, int col) {
        this.board = new MinesweeperBoard(row, col);
    }

    /**
     * Return whether all the non-bomb tiles have been tapped
     *
     * @return whether all the non-bomb tiles have been tapped
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
            MinesweeperManager.scores.addScore(newScore);
            //if number of bombs == number of untapped spaces the game ends
            return true;
        }else{
            return false;
        }


    }

    /**
     * Return whether the tap is valid (the tile hasn't been tapped before or the game is in a
     * losing state.
     *
     * @param position the tile to check
     * @return whether the tap is valid
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
            board.generateBoard(board.getNumRows(), board.getNumCols());
        }else if(tile.getId() == MinesweeperTile.BOMB_ID){
            tile.setId(MinesweeperTile.EXPLODED_BOMB_ID);
            setGameLost(true);
        }

    }

    /**
     * Sets whether the game has been lost. If it matches the current state, do nothing. Otherwise,
     * if it is changing from not lost to lost then it reveals the board.
     *
     * @param lost whether the game has been lost
     */
    private void setGameLost(boolean lost){
        if(lost == this.gameLost){
            return;
        }
        this.gameLost = lost;

        if(lost){
            for(int i = 0; i < board.getNumRows(); i++){
                for(int j = 0; j < board.getNumCols(); j++){
                    board.tapTile(i, j);
                }
            }
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

    /**
     * Return the score board for this game.
     *
     * @return the score board for this game.
     */
    @Override
    public ScoreBoard getScoreBoard() {return scores;}

    @Override
    public void autoSave(String fileName) {
        game.saveToFile(fileName);
    }
}