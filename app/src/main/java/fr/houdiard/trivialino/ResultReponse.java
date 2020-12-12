package fr.houdiard.trivialino;

public class ResultReponse {
    private String question;
    private Boolean tagName;
    private String correct;
    private String answered;
    private String Categ;



    public ResultReponse(String question, Boolean tagName, String correct, String answered, String categ) {
        this.question = question;
        this.tagName = tagName;
        this.correct = correct;
        this.answered = answered;
        this.Categ = categ;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getTagName() {
        return tagName;
    }

    public void setTagName(Boolean tagName) {
        this.tagName = tagName;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public String getCateg() {
        return Categ;
    }

    public void setCateg(String categ) {
        Categ = categ;
    }
}
