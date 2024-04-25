package oslomet.oblig3webapp;

public class Bilett {


        private int id;
        private String film;
        private int antall;
        private String fornavn;
        private String etternavn;
        private String telefonnummer;
        private String epost;



        public Bilett(int id, String film, int antall, String fornavn, String etternavn, String telefonnummer, String epost)  {
            this.id=id;
            this.film = film;
            this.antall = antall;
            this.fornavn = fornavn;
            this.etternavn = etternavn;
            this.telefonnummer = telefonnummer;
            this.epost = epost;


        }

        public Bilett() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFilm() {
            return film;
        }

        public void setFilm(String film) {
            this.film = film;
        }

        public int getAntall() {
            return antall;
        }

        public void setAntall(int antall) {
            this.antall = antall;
        }
        public String getFornavn() {
            return fornavn;
        }

        public void setFornavn(String fornavn) {
            this.fornavn = fornavn;
        }

        public String getEtternavn() {
            return etternavn;
        }

        public void setEtternavn(String etternavn) {
            this.etternavn = etternavn;
        }

        public String getTelefonnummer() {
            return telefonnummer;
        }

        public void setTelefonnummer(String telefonnummer) {
            this.telefonnummer = telefonnummer;
        }

        public String getEpost() {
            return epost;
        }

        public void setEpost(String epost) {
            this.epost = epost;
        }


    }



