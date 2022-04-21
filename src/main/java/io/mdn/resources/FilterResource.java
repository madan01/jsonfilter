package io.mdn.resources;

import com.jayway.jsonpath.JsonPath;
import io.mdn.JsonFilterConfiguration;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by madan on 29/07/20.
 **/

@Slf4j
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class FilterResource {
    private JsonFilterConfiguration configuration;
    private OkHttpClient okHttpClient;

    @Inject
    public FilterResource(JsonFilterConfiguration configuration,
                          OkHttpClient okHttpClient) {
        this.configuration = configuration;
        this.okHttpClient=okHttpClient;
    }

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntityVariables(@QueryParam("url") String url,@QueryParam("exp") String exp) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
        Object dataObject = JsonPath.parse(jsonString).read(exp);
        String dataString = dataObject.toString();
        return Response.status(200).entity(dataString).build();
    }

}
