package org.dng;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//public class Service implements IStatisticService{
public class Service {
    private static String log="";
    //add record of method result in to log
    public static void log(String record){
        StringBuilder sb = new StringBuilder();
        sb.append(log);
        sb.append(record+"\n");
        log = sb.toString();
    }
    public static void logPrint(){
        System.out.println(log);
    }

    //добавляет рейтинг игроку, в случае его выигрыша в игре
    public static void increaseRating(Player player) {
        player.increasePlayerRating();
        log("Service.increaseRating(player) player = `"+player.getNicName()+"` done successfully");
    }

    //выводит список игр, в которые играют все игроки на сайте
    public static List<Game> getAllPlayersGamesList(List<Player> players) {
        HashSet<Game> gamesSet = new HashSet<>();
        for (Player player:players){
            for(Game game:player.getGamesSet()){
                gamesSet.add(game);
            }
        }
        return gamesSet.stream().toList();
    }

    //выводит рейтинг по имени игрока и игре
    public static int getPlayersRating(Player player, Game game) {
        return game.getPlayerAndRating().get(player);
    }

    //выводит 10 лучших игроков в определенной игре
    public static List<Player> get10BestPlayers(Game game) {
        return game.getPlayers().stream()
//                .sorted((o1, o2) -> o1.getPlayerRating() - o2.getPlayerRating())
                .sorted(Comparator.comparingInt(Player::getPlayerRating))
                .limit(10)
                .toList();
    }

    //выводит 10 лучших игроков с учетом всех игр
    public static List<Player> get10BestPlayers(Set<Game> games) {
        return games.stream()
                .flatMap((g)->g.getPlayers().stream())
                .sorted(Comparator.comparingInt(Player::getPlayerRating))
                .limit(10)
                .toList();
    }
}