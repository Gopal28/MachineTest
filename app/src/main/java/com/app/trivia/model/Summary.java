package com.app.trivia.model;

public class Summary {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getSecondAns() {
        return secondAns;
    }

    public void setSecondAns(String secondAns) {
        this.secondAns = secondAns;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    private String userName = "";

    public String getThirdAns() {
        return thirdAns;
    }

    public void setThirdAns(String thirdAns) {
        this.thirdAns = thirdAns;
    }

    private String thirdAns = "";

    private String secondAns = "";

    private String dateTime = "";

}
