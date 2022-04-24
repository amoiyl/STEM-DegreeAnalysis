package org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository;

public class Result {
    private String state;
    private String degreeEarnedByMen;
    private String degreeEarnedByWomen;

    public Result(String state,  String degreeEarnedByMen, String degreeEarnedByWomen) {
        this.state = state;
        this.degreeEarnedByMen = degreeEarnedByMen;
        this.degreeEarnedByWomen = degreeEarnedByWomen;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDegreeEarnedByMen() {
        return degreeEarnedByMen;
    }

    public void setDegreeEarnedByMen(String degreeEarnedByMen) {
        this.degreeEarnedByMen = degreeEarnedByMen;
    }

    public String getDegreeEarnedByWomen() {
        return degreeEarnedByWomen;
    }

    public void setDegreeEarnedByWomen(String degreeEarnedByWomen) {
        this.degreeEarnedByWomen = degreeEarnedByWomen;
    }

    public String toString() {
        return "In " + getState() + ", there are " +
                getDegreeEarnedByMen() + " STEM degrees earned by men and " +
                getDegreeEarnedByWomen() + " STEM degrees earned by women.";
    }
}
