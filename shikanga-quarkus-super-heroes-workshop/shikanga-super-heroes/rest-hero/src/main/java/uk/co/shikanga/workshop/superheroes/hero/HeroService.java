package uk.co.shikanga.workshop.superheroes.hero;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class HeroService {

    private final HeroRepository heroRepository;

    @ConfigProperty(name = "level.multiplier", defaultValue = "1")
    int levelMultiplier;

    @Inject
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Transactional(SUPPORTS)
    public List<Hero> findAllHeroes() {
        return heroRepository.listAll();
    }

    @Transactional(SUPPORTS)
    public Hero findHeroById(Long id) {
        return heroRepository.findById(id);
    }

    @Transactional(SUPPORTS)
    public Hero findRandomHero() {
        Hero randomHero = null;
        while (randomHero == null) {
            randomHero = heroRepository.findRandom();
        }
        return randomHero;
    }

    public Hero persistHero(@Valid Hero hero) {
        hero.setLevel(hero.getLevel() * levelMultiplier);
        heroRepository.persist(hero);
        return hero;
    }

    public Hero updateHero(Long id, @Valid Hero hero) {
        Hero entity = heroRepository.findById(id);
        entity.setName(hero.getName());
        entity.setOtherName(hero.getOtherName());
        entity.setLevel(hero.getLevel());
        entity.setPicture(hero.getPicture());
        entity.setPowers(hero.getPowers());
        return entity;
    }

    public void deleteHero(Long id) {
        Hero hero = heroRepository.findById(id);
        heroRepository.delete(hero);
    }

}
