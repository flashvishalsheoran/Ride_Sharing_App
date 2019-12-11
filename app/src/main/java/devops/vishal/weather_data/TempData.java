package devops.vishal.weather_data;


public class TempData {

    private String from;
    private String to;
    private String from_time;
    private String to_time;

    private String value;
    private String currency_symbol;
    private String trip_duration_in_mins;

    public TempData() {
    }

    public TempData(String from, String to, String from_time, String to_time, String value, String currency_symbol, String trip_duration_in_mins) {
        this.from = from;
        this.to = to;
        this.from_time = from_time;
        this.to_time = to_time;
        this.value = value;
        this.currency_symbol = currency_symbol;
        this.trip_duration_in_mins = trip_duration_in_mins;
    }



    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public String getTrip_duration_in_mins() {
        return trip_duration_in_mins;
    }

    public void setTrip_duration_in_mins(String trip_duration_in_mins) {
        this.trip_duration_in_mins = trip_duration_in_mins;
    }
}
