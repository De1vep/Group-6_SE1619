
package model;


public class Slider {
    
    private int sliderId;
    private String sliderTitle, image, link, note;
    private boolean status;

    public Slider() {
    }

    public Slider(int sliderId, String sliderTitle, String image, String link, String note, boolean status) {
        this.sliderId = sliderId;
        this.sliderTitle = sliderTitle;
        this.image = image;
        this.link = link;
        this.note = note;
        this.status = status;
    }

    public int getSliderId() {
        return sliderId;
    }

    public void setSliderId(int sliderId) {
        this.sliderId = sliderId;
    }

    public String getSliderTitle() {
        return sliderTitle;
    }

    public void setSliderTitle(String sliderTitle) {
        this.sliderTitle = sliderTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slider{" + "sliderId=" + sliderId + ", sliderTitle=" + sliderTitle + ", image=" + image + ", link=" + link + ", note=" + note + ", status=" + status + '}';
    }
    
    
    

}
