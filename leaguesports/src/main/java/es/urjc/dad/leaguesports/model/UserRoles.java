package es.urjc.dad.leaguesports.model;

public enum UserRoles {
    User,
    Organizer,
    Admin;

    @Override
    public String toString(){
        
        switch(ordinal()){
            case 0: return "Usuario";
            case 1: return "Organizador";
            case 2: return "Admin";
            default: return "-";
        }
    }
}
