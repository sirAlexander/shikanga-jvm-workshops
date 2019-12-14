package uk.co.shikanga.workshop.superheroes.hero;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class HeroRepository implements PanacheRepository<Hero> {

    public Hero findRandom(){
        long countHeroes = this.count();
        Random random = new Random();
        int randomHero = random.nextInt((int)countHeroes);
        return this.findAll().page(randomHero, 1).firstResult();
    }
}
