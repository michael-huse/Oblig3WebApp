package oslomet.oblig3webapp;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import oslomet.oblig3webapp.Bilett;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


    @RestController
    public class BestillingController {

        @Autowired //henter kinorep
        kinoBilettRepo rep;

        @PostMapping("/lagreBilett")
        public void lagreBilett(Bilett bilett, HttpServletResponse response) throws IOException {
            if(!rep.lagreBilett(bilett)) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
            }
        }

        @GetMapping("/hentAlle")
        public List<Bilett> hentAlle(HttpServletResponse response) throws IOException {
            List<Bilett> alleBiletter = rep.hentAlle();
            if (alleBiletter==null) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
            }
            return rep.hentAlle();
        }


        @GetMapping("/hentFilmer")
        public List<Filmer> hentFilmer() {
            List<Filmer> listFilmer = new ArrayList<>();
            listFilmer.add(new Filmer("Titanic"));
            listFilmer.add(new Filmer("Eternal sunshine of the spotless mind"));
            listFilmer.add(new Filmer("The iron giant"));
            return listFilmer;

        }



        @GetMapping("/slettAlle")
        public void slettAlle(HttpServletResponse response) throws IOException{
            if(!rep.slettAlle()){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - Prøv igjen senere");
            }
        }


        @GetMapping("/hentEnBilett")
        public Bilett hentEnBilett(int id, HttpServletResponse response) throws IOException{
            Bilett biletten = rep.hentEnBilett(id);
            if(biletten==null){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
            }
            return rep.hentEnBilett(id);
        }

        @PostMapping("/endreBilett")
        public void endreBilett(Bilett bilett, HttpServletResponse response) throws IOException{
            if(!rep.endreBilett(bilett)){
                response.sendError((HttpStatus.INTERNAL_SERVER_ERROR.value()),"Feil i DB - Prøv igjen senere");
            }
        }

        @GetMapping("/slettEnBilett")
        public void slettEnBilett(int id, HttpServletResponse response) throws IOException {
            if (!rep.slettEnBilett(id)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
            }
        }

    }

