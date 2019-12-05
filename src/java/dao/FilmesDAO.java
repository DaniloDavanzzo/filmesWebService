/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filme;

public class FilmesDAO {

    public FilmesDAO() {

    }

    public boolean inserir(Filme filme) {
        String sql = "INSERT INTO filme (title,description,synopsis,link_img) VALUES (?,?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1, filme.getTitle());
            pst.setString(2, filme.getDescription());
            pst.setString(3, filme.getSynopsis());
            pst.setString(4, filme.getLink_img());

            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmesDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }

        return retorno;
    }

    public boolean atualizar(Filme filme) {
        String sql = "UPDATE filme set title=?,description=?,synopsis=?, link_img=? where id=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1, filme.getTitle());
            pst.setString(2, filme.getDescription());
            pst.setString(3, filme.getSynopsis());
            pst.setString(4, filme.getLink_img());
            pst.setInt(5, filme.getId());

            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmesDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }

        return retorno;
    }

    public boolean excluir(Filme filme) {
        String sql = "DELETE FROM filme where login=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setInt(1, filme.getId());
            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmesDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }

    public List<Filme> listar() {
        String sql = "SELECT * FROM filmes";
        List<Filme> retorno = new ArrayList<Filme>();

        PreparedStatement pst = Conexao.getPreparedStatement(sql);

        try {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Filme item = new Filme();
                item.setId(res.getInt("id"));
                item.setTitle(res.getString("title"));
                item.setDescription(res.getString("description"));
                item.setSynopsis(res.getString("synopsis"));
                item.setLink_img(res.getString("link_img"));

                retorno.add(item);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Filme buscar(Filme filme) {
        String sql = "SELECT * FROM filmes where id=?";
        Filme retorno = null;

        PreparedStatement pst = Conexao.getPreparedStatement(sql);

        try {
            pst.setInt(1, filme.getId());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                retorno = new Filme();
                retorno.setId(res.getInt("id"));
                retorno.setTitle(res.getString("title"));
                retorno.setDescription(res.getString("description"));
                retorno.setSynopsis(res.getString("synopsis"));
                retorno.setLink_img(res.getString("link_img"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilmesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
