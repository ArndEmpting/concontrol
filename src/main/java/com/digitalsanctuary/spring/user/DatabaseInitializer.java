package com.digitalsanctuary.spring.user;

import com.digitalsanctuary.spring.user.persistence.model.Altersklasse;
import com.digitalsanctuary.spring.user.persistence.model.Clan;
import com.digitalsanctuary.spring.user.persistence.model.Zahlungsinformation;
import com.digitalsanctuary.spring.user.persistence.repository.AltersklassenRepository;
import com.digitalsanctuary.spring.user.persistence.repository.ClanRepository;
import com.digitalsanctuary.spring.user.persistence.repository.ZahlungsinfromationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private AltersklassenRepository altersklassenRepository;
    @Autowired
    private ClanRepository clanRepository;
    @Autowired
    private ZahlungsinfromationRepository zahlungsinfromationRepository;

    @Autowired
    private AppProperties appConfig;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            if (appConfig.isReset()) {
                altersklassenRepository.deleteAll();
            }
            if (altersklassenRepository.count() == 0) {
                System.out.println(appConfig.getAltersklassen());
                for (String s : appConfig.getAltersklassen()) {
                    Altersklasse altersklasse = new Altersklasse();
                    altersklasse.setName(s);
                    // set any other necessary properties on the registration object

                    altersklassenRepository.save(altersklasse);
                }
            }
            clanRepository.deleteAll();
            System.out.println(appConfig.getClans());
            for (String s : appConfig.getClans()) {
                Clan clan = new Clan();
                Clan clanTross = new Clan();
                clan.setName(s);
                clanTross.setName(s + " Tross");
                System.out.println(s);
                // set any other necessary properties on the registration object
                clanRepository.save(clan);
                clanRepository.save(clanTross);
            }
            if (zahlungsinfromationRepository.count() == 0) {
                Zahlungsinformation zf = new Zahlungsinformation();
                zf.setKontoinhaber("Kontoinhaber");
                zf.setIBAN("DE12345678901234567890");
                zf.setBIC("BIC");
                zahlungsinfromationRepository.save(zf);
            }
        };
    }
}