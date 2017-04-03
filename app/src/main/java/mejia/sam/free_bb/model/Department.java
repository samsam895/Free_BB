
package mejia.sam.free_bb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Department {

    @SerializedName("Launchpad")
    @Expose
    private List<Person> launchpad = null;
    @SerializedName("CXP")
    @Expose
    private List<Person> cXP = null;
    @SerializedName("Mobile")
    @Expose
    private List<Person> mobile = null;

    public List<Person> getLaunchpad() {
        return launchpad;
    }

    public void setLaunchpad(List<Person> launchpad) {
        this.launchpad = launchpad;
    }

    public List<Person> getCXP() {
        return cXP;
    }

    public void setCXP(List<Person> cXP) {
        this.cXP = cXP;
    }

    public List<Person> getMobile() {
        return mobile;
    }

    public void setMobile(List<Person> mobile) {
        this.mobile = mobile;
    }

}
