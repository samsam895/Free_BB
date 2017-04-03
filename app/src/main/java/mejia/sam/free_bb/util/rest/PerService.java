package mejia.sam.free_bb.util.rest;

import mejia.sam.free_bb.model.Department;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by User on 4/1/2017.
 */

public interface PerService {
    @GET("/backbase/members.php")
    Call<Department> getPersons();
}