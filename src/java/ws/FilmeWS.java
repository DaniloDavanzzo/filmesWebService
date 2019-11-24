/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import com.google.gson.Gson;
import dao.FilmesDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import modelo.Filme;
import org.jboss.logging.Param;


@Path("filme")
public class FilmeWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FilmeWS
     */
    
    public FilmeWS() {
    }

    /**
     * Retrieves representation of an instance of ws.FilmeWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/text")
    @Path("Filme/get")
    public String getJson() {
        return "meu primeiro WS RESTFULL";
    }
    
    @GET
    @Produces("application/json")
    @Path("Filme/get/{id}")
    public String getFilme(@PathParam("id") Integer id)
    {
        Filme u = new Filme();
        u.setId(id);
        
        FilmesDAO dao = new FilmesDAO();
        u = dao.buscar(u);
       
        //Converter para Gson
        Gson g = new Gson();
        return g.toJson(u);
    }

    @GET
    @Produces("application/json")
    @Path("Filme/list")
    public String listFilmes()
    {
        List<Filme> lista;
        
        FilmesDAO dao = new FilmesDAO();
        lista = dao.listar();
        
        //Converter para Gson
        Gson g = new Gson();
        return g.toJson(lista);
    }
    
    /**
     * PUT method for updating or creating an instance of FilmeWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
