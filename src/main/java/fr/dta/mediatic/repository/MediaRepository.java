package fr.dta.mediatic.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.Media;

@Repository
@Transactional
public class MediaRepository extends AbstractRepository<Media> {

    @Override
    protected Class<Media> getEntityClass() {
	return Media.class;
    }

}
