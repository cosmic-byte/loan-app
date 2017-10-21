package thecrevance.elasticrepo;

import thecrevance.dto.UserDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepositoryES extends ElasticsearchRepository<UserDto, Long> {
}
