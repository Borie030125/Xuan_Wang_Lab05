import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class RockPaperScissorsGame extends Application {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    private final TextArea resultsArea = new TextArea();
    private final Label playerWinsLabel = new Label("Player Wins: 0");
    private final Label computerWinsLabel = new Label("Computer Wins: 0");
    private final Label tiesLabel = new Label("Ties: 0");

    @Override
    public void start(Stage primaryStage) {
        // Main layout container
        BorderPane root = new BorderPane();

        // Buttons for player choices
        HBox choicesBox = new HBox(10);
        Button rockButton = new Button("Rock");
        Button paperButton = new Button("Paper");
        Button scissorsButton = new Button("Scissors");
        choicesBox.getChildren().addAll(rockButton, paperButton, scissorsButton);
        choicesBox.setAlignment(Pos.CENTER);

        // Stats panel
        VBox statsBox = new VBox(5);
        statsBox.getChildren().addAll(playerWinsLabel, computerWinsLabel, tiesLabel);
        statsBox.setAlignment(Pos.CENTER);

        // Setup results area
        resultsArea.setEditable(false);
        root.setBottom(resultsArea);

        // Event handling for buttons
        rockButton.setOnAction(e -> playGame("Rock"));
        paperButton.setOnAction(e -> playGame("Paper"));
        scissorsButton.setOnAction(e -> playGame("Scissors"));

        root.setTop(choicesBox);
        root.setCenter(statsBox);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Rock Paper Scissors Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[new Random().nextInt(choices.length)];
        String result;

        if (playerChoice.equals(computerChoice)) {
            result = "It's a tie!";
            ties++;
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = playerChoice + " beats " + computerChoice + " (Player wins)";
            playerWins++;
        } else {
            result = computerChoice + " beats " + playerChoice + " (Computer wins)";
            computerWins++;
        }

        // Update the UI
        updateUI(result);
    }

    private void updateUI(String result) {
        resultsArea.appendText(result + "\n");
        playerWinsLabel.setText("Player Wins: " + playerWins);
        computerWinsLabel.setText("Computer Wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
