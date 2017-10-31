package thecrevance.elasticrepo;

import thecrevance.model.PreUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepositoryES extends ElasticsearchRepository<PreUser, Long> {
}
