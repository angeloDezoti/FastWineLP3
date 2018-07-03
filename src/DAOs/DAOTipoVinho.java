package DAOs;


import Entidades.TipoVinho;
import DAOs.DAOGenerico;
import static DAOs.DAOGenerico.em;
import java.util.ArrayList;
import java.util.List;

public class DAOTipoVinho extends DAOGenerico<TipoVinho> {

    public DAOTipoVinho() {
        super(TipoVinho.class);
    }

    public int autoIdTipoVinho() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoVinho) FROM TipoVinho e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoVinho> listByNome(String nome) {
        return em.createQuery("SELECT e FROM TipoVinho e WHERE e.nomeTipo LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<TipoVinho> listById(int id) {
        return em.createQuery("SELECT e FROM TipoVinho e WHERE e.idTipo = :id").setParameter("id", id).getResultList();
    }

    public List<TipoVinho> listInOrderNome() {
        return em.createQuery("SELECT e FROM TipoVinho e ORDER BY e.nomeTipo").getResultList();
    }

    public List<TipoVinho> listInOrderId() {
        return em.createQuery("SELECT e FROM TipoVinho e ORDER BY e.idTipo").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoVinho> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTipo() + "-" + lf.get(i).getNomeTipo());
        }
        return ls;
    }
public static void main(String[] args) {
        DAOTipoVinho daoTipoVinho = new DAOTipoVinho();
        List<TipoVinho> listaTipoVinho = daoTipoVinho.list();
        for (TipoVinho tipoVinho : listaTipoVinho) {
            System.out.println(tipoVinho.getIdTipo() + "-" + tipoVinho.getNomeTipo());
        }
    }}
