package es.urjc.dad.leaguesports.model;

public enum GameResults {
    None,
    LocalWins,
    visitorWins,
    Draw;

    @Override
    public String toString(){
        
        switch(ordinal()){
            case 0: return "-";
            case 1: return "Victoria Equipo 1";
            case 2: return "Victoria Equipo 2";
            case 3: return "Empate";
            default: return "/";
        }
    }
}
