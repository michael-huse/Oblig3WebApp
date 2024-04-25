package oslomet.oblig3webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;



    @Repository
    public class kinoBilettRepo {
        @Autowired
        private JdbcTemplate db;

        private Logger logger = LoggerFactory.getLogger(kinoBilettRepo.class);

        public boolean lagreBilett(Bilett nyBilett) {
            String sql = "INSERT INTO Bilett(film, antall, fornavn, etternavn, telefonnummer, epost) VALUES(?,?,?,?,?,?)";

            try{
                db.update(sql,
                        nyBilett.getFilm(),
                        nyBilett.getAntall(),
                        nyBilett.getFornavn(),
                        nyBilett.getEtternavn(),
                        nyBilett.getTelefonnummer(),
                        nyBilett.getEpost());
                return true;
            }catch (Exception e){
                logger.error("Feil i lagreBilett : "+ e);
                return false;
            }
        }

        public List<Bilett> hentAlle() {
            String sql = "SELECT * FROM Bilett ORDER BY etternavn ASC";
            try{
                List<Bilett> alleBiletter = db.query(sql, new BeanPropertyRowMapper(Bilett.class));
                return alleBiletter;
            }
            catch (Exception e) {
                logger.error("Feil i hentAlleBiletter :" + e);
                return null;
            }
        }

        public boolean slettAlle() {
            String sql = "DELETE FROM Bilett";

            try {
                db.update(sql);
                return true;
            }
            catch (Exception e) {
                logger.error("feil i slett alle: " + e);
                return false;
            }
        }

        public boolean slettEnBilett(int id) {
            String sql = "DELETE FROM Bilett WHERE id=?";

            try {
                db.update(sql, id);
                return true;
            } catch (Exception e) {
                logger.error("Feil i slettEnBilett" + e);
                return false;
            }
        }


        public Bilett hentEnBilett ( int id){
            String sql = "SELECT * FROM Bilett WHERE id=?";
            try {
                Bilett enBilett = db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Bilett.class),id);
                return enBilett;
            } catch (Exception e) {
                logger.error("Feil i hentEnBilett" + e);
                return null;
            }
        }

        public boolean endreBilett (Bilett bilett){
            String sql = "UPDATE Bilett SET film=?,antall=?,fornavn=?,etternavn=?,telefonnummer=?, epost=? where id=?";

            try {
                db.update(sql,
                        bilett.getFilm(),
                        bilett.getAntall(),
                        bilett.getFornavn(),
                        bilett.getEtternavn(),
                        bilett.getTelefonnummer(),
                        bilett.getEpost(),
                        bilett.getId());
                return true;
            } catch (Exception e) {
                logger.error("Feil i endreBilett: " + e);
                return false;
            }
        }

    }


