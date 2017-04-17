/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Shash.myapplication.backend;

import com.example.JokeGenerator;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Shash.example.com",
                ownerName = "backend.myapplication.Shash.example.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    @ApiMethod(name = "get_joke")
    public Joke getJoke() {
        Joke response = new Joke();
        response.setData(new JokeGenerator().getRandomJoke());
        return response;
    }

}
