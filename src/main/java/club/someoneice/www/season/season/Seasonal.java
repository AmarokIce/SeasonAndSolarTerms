package club.someoneice.www.season.season;

public enum Seasonal {
    SPRING(0),
    RAIN(5),
    STINGED(10),
    VERNAL(15),



    public final int dayStart;
    Seasonal(int day) {
        this.dayStart = day;
    }
}
