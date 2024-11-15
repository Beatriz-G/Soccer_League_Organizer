import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.AllTeams;
import com.teamtreehouse.model.TeamSetup;



public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);

    TeamSetup setup = new TeamSetup();
    setup.run();

    // AllTeams allTeams = new AllTeams();
    // TeamSetup setup  = new TeamSetup();
    // TeamSetup.run();
  }
}
