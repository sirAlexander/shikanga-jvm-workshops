package uk.co.shikanga.workshop.superheroes.hero;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/api/heroes")
@Produces(APPLICATION_JSON)
public final class HeroResource {

    private static final Logger LOGGER = Logger.getLogger(HeroResource.class);

    @Inject
    HeroService service;

    @GET
    @Path("/random")
    public Response getRandomHero() {
        Hero hero = service.findRandomHero();
        LOGGER.debug("Found random hero " + hero);
        return Response.ok(hero).build();
    }

    @GET
    public Response getAllHeroes() {
        List<Hero> heroes = service.findAllHeroes();
        LOGGER.debug("Total number of heroes " + heroes);
        return Response.ok(heroes).build();
    }

    @GET
    @Path("/{id}")
    public Response getHero(
            @PathParam("id") Long id) {
        Hero hero = service.findHeroById(id);
        if (hero != null) {
            LOGGER.debug("Found hero " + hero);
            return Response.ok(hero).build();
        } else {
            LOGGER.debug("No hero found with id " + id);
            return Response.noContent().build();
        }
    }

    @POST
    public Response createHero(
            @Valid Hero hero, @Context UriInfo uriInfo) {
        hero = service.persistHero(hero);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(hero.getId()));
        LOGGER.debug("New hero created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    @PUT
    public Response updateHero(
            @Valid Hero hero) {
        hero = service.updateHero(hero);
        LOGGER.debug("Hero updated with new valued " + hero);
        return Response.ok(hero).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteHero(
            @PathParam("id") Long id) {
        service.deleteHero(id);
        LOGGER.debug("Hero deleted with " + id);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello hero";
    }
}