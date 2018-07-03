package DAOs;


import Entidades.Uva;
import DAOs.DAOGenerico;
import static DAOs.DAOGenerico.em;
import java.util.ArrayList;
import java.util.List;

public class DAOUva extends DAOGenerico<Uva> {

    public DAOUva() {
        super(Uva.class);
    }

    public int autoIdUva() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idUva) FROM Uva e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Uva> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Uva e WHERE e.nomeUva LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Uva> listById(int id) {
        return em.createQuery("SELECT e FROM Uva e WHERE e.idUva = :id").setParameter("id", id).getResultList();
    }

    public List<Uva> listInOrderNome() {
        return em.createQuery("SELECT e FROM Uva e ORDER BY e.nomeUva").getResultList();
    }

    public List<Uva> listInOrderId() {
        return em.createQuery("SELECT e FROM Uva e ORDER BY e.idUva").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Uva> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdUva() + "-" + lf.get(i).getNomeUva());
        }
        return ls;
    }
public static void main(String[] args) {
        DAOUva daoUva = new DAOUva();
        List<Uva> listaUva = daoUva.list();
        for (Uva uva : listaUva) {
            System.out.println(uva.getIdUva() + "-" + uva.getNomeUva());
        }
    }}
