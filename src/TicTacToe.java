import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private static final int BOARD_SIZE = 5;
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";

    private String currentPlayer = PLAYER_X;
    private Button[][] buttons = new Button[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = createBoard();

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createBoard() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = new Button();
                button.setMinSize(60, 60);
                button.setOnAction(e -> handleButtonClick(button));

                gridPane.add(button, col, row);
                buttons[row][col] = button;
            }
        }

        return gridPane;
    }

    private void handleButtonClick(Button button) {
        if (button.getText().isEmpty()) {
            button.setText(currentPlayer);

            if (checkForWin()) {
                showAlert("Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                showAlert("It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer.equals(PLAYER_X)) ? PLAYER_O : PLAYER_X;
            }
        }
    }

    private boolean checkForWin() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }

        return checkDiagonals();
    }

    private boolean checkRow(int row) {
        for (int i = 0; i < BOARD_SIZE - 4; i++) {
            if (buttons[row][i].getText().equals(currentPlayer) &&
                    buttons[row][i + 1].getText().equals(currentPlayer) &&
                    buttons[row][i + 2].getText().equals(currentPlayer) &&
                    buttons[row][i + 3].getText().equals(currentPlayer) &&
                    buttons[row][i + 4].getText().equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn(int col) {
        for (int i = 0; i < BOARD_SIZE - 4; i++) {
            if (buttons[i][col].getText().equals(currentPlayer) &&
                    buttons[i + 1][col].getText().equals(currentPlayer) &&
                    buttons[i + 2][col].getText().equals(currentPlayer) &&
                    buttons[i + 3][col].getText().equals(currentPlayer) &&
                    buttons[i + 4][col].getText().equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        for (int i = 0; i < BOARD_SIZE - 4; i++) {
            for (int j = 0; j < BOARD_SIZE - 4; j++) {
                if (buttons[i][j].getText().equals(currentPlayer) &&
                        buttons[i + 1][j + 1].getText().equals(currentPlayer) &&
                        buttons[i + 2][j + 2].getText().equals(currentPlayer) &&
                        buttons[i + 3][j + 3].getText().equals(currentPlayer) &&
                        buttons[i + 4][j + 4].getText().equals(currentPlayer)) {
                    return true;
                }

                if (buttons[i][j + 4].getText().equals(currentPlayer) &&
                        buttons[i + 1][j + 3].getText().equals(currentPlayer) &&
                        buttons[i + 2][j + 2].getText().equals(currentPlayer) &&
                        buttons[i + 3][j + 1].getText().equals(currentPlayer) &&
                        buttons[i + 4][j].getText().equals(currentPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetGame() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = PLAYER_X;
    }
}
