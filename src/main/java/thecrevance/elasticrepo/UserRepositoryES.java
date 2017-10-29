package thecrevance.elasticrepo;

import thecrevance.dto.PreUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepositoryES extends ElasticsearchRepository<PreUser, Long> {
}
