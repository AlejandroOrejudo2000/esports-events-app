package es.urjc.dad.leaguesports.model;

public enum UserRoles {
    User,
    Admin;

    @Override
    public String toString(){
        
        switch(ordinal()){
            case 0: return "Usuario";
            case 1: return "Admin";
            default: return "-";
        }
    }
}
