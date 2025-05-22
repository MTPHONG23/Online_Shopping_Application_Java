
package Model;


public class Bill {
    private Integer id;
    private String time;
    private String date;
    private Float total;
	private String status;
    

    public Bill(Integer id, String time,String date, Float total, String status) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.date= date;
        this.status= status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
