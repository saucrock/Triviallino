package fr.houdiard.trivialino;

public class AReponse {
    String reponse;
    Boolean verif;

    public AReponse(String reponse, Boolean verif) {
        this.reponse = reponse;
        this.verif = verif;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Boolean getVerif() {
        return verif;
    }

    public void setVerif(Boolean verif) {
        this.verif = verif;
    }
}
