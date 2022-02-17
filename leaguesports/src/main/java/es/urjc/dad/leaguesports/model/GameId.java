package es.urjc.dad.leaguesports.model;

import java.io.Serializable;

public class GameId implements Serializable {

    private long tournament;

    private long number;

    public long getTournamentId() {
        return tournament;
    }

    public void setTournamentId(long tournamentId) {
        this.tournament = tournamentId;
    }

    public long getGameNumber() {
        return number;
    }

    public void setGameNumber(long gameNumber) {
        this.number = gameNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (number ^ (number >>> 32));
        result = prime * result + (int) (tournament ^ (tournament >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameId other = (GameId) obj;
        if (number != other.number)
            return false;
        if (tournament != other.tournament)
            return false;
        return true;
    }
    
    
}
